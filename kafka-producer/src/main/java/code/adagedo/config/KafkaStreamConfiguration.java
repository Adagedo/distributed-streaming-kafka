package code.adagedo.config;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamConfiguration.class);


    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "realtime-stream-v2");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public StreamsBuilderFactoryBeanConfigurer configurer() {
        return factoryBean -> factoryBean.setStateListener(
                ((newState, oldState) -> {
                    LOGGER.info("state transition from {} to {}", oldState, newState );
                })
        );
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("streaming-recent-changes");

        stream
                .mapValues((ValueMapper<String, String>) String::toUpperCase)
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMillis(1_000)))
                .reduce((value1, value2) -> value1 + value2, Named.as("windowStore"))
                .toStream()
                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
                .filter((key, value) -> value.length() > 40)
                .foreach((key, value) -> LOGGER.info("Processed Result: Key={}, Value={}", key, value));

        return stream;
    }
}