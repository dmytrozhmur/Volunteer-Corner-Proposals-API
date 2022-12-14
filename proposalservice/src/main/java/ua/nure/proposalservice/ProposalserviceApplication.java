package ua.nure.proposalservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ConfigurationPropertiesScan
public class ProposalserviceApplication {
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProposalserviceApplication.class, args);
    }
}
