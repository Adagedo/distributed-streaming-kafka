package code.adagedo.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> template;

    void publishEvents(String message){
        String topic = "streaming-recent-changes";
        template.send(topic, message);
    }
}
//https://stream.wikimedia.org/v2/stream/recentchange