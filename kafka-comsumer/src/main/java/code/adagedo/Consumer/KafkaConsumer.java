package code.adagedo.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "distributed_streaming", groupId = "realtime-stream-v2")
    public void consume(String eventMessage) {
        LOGGER.info("Event message received, {}", eventMessage);
        System.out.println("Event message received: " + eventMessage );
    }
}
