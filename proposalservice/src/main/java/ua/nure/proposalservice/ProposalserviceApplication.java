package ua.nure.proposalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.nure.proposalservice.repos.HelpProposalRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProposalserviceApplication {
    @Autowired
    private HelpProposalRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ProposalserviceApplication.class, args);
    }

    @PostConstruct
    public void checkRepository() {
        System.out.println(repository.findAll());
    }
}
