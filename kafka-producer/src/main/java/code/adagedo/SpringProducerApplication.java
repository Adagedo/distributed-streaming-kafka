package code.adagedo;

import code.adagedo.producer.KafkaProducer;
import org.jspecify.annotations.NonNull;
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
    public void run(String @NonNull ... args) throws Exception {
        System.out.println(">>> App booted successfully! Triggering Wikimedia producer...");
        producer.sendMessage();
    }
}
