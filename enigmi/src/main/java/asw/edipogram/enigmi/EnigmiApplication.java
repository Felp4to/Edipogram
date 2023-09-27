package asw.edipogram.enigmi;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableDiscoveryClient 
public class EnigmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmiApplication.class, args);
	}

}
