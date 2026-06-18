package code.adagedo.Consumer;

import code.adagedo.models.StreamData;
import code.adagedo.repository.StreamRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final StreamRepository repository;

    @KafkaListener(topics = "distributed_streaming", groupId = "realtime-stream-v2")
    public void consume(String eventMessage) {
        StreamData data = new StreamData();
        data.setWikiEventData(eventMessage);
        repository.save(data);
    }
}
