package code.adagedo.producer;

import code.adagedo.handler.WikimediaHandler;
import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
public class KafkaProducer{

    private final KafkaTemplate<String, String> template;
    private final String wikimediaUrl;

    public KafkaProducer(KafkaTemplate<String, String> template, @Value("${wikimedia.url}") String wikimediaUrl) {
        this.template = template;
        this.wikimediaUrl = wikimediaUrl;
    }

    public void sendMessage() throws StreamException, InterruptedException {
        String topic = "streaming-recent-changes";
        BackgroundEventHandler eventHandler = new WikimediaHandler(template, topic);
        EventSource eventSourceBuilder = buildSource(eventHandler);
        eventSourceBuilder.start();
        Thread.sleep(Long.MAX_VALUE);
    }

    private EventSource buildSource(BackgroundEventHandler backgroundEventHandler){
        BackgroundEventSource eventSource = new BackgroundEventSource.Builder(
                backgroundEventHandler,
                new EventSource.Builder(ConnectStrategy.http(URI.create(wikimediaUrl))
                        .connectTimeout(5, TimeUnit.SECONDS))
        ).build();

        return eventSource.getEventSource();
    }
}