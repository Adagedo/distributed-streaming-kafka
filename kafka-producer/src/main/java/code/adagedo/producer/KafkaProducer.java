package code.adagedo.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaProducer {

    private KafkaTemplate<String, String> template;

    void sendStreams(String message){
        String topic = "streaming-recent-changes";
    }


}
