package code.adagedo;

import code.adagedo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProducerApplication implements CommandLineRunner {
    public static void main( String[] args ) {
        SpringApplication.run(SpringProducerApplication.class);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */

    @Autowired
    private KafkaProducer producer;


    @Override
    public void run(String... args) throws Exception {
        producer.sendMessage();
    }
}
