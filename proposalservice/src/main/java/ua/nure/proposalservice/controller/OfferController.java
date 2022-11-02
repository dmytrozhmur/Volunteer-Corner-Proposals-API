package ua.nure.proposalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.service.OfferService;

import java.util.List;

@RestController
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("/api/v1/proposals")
    private List<ProposalInfo> allProposals() {
        return offerService.getAllProposals();
    }

    @PostMapping("/api/v1/proposal")
    private ProposalInfo newProposal(@RequestBody ProposalCreation newProposal) {
        return offerService.addProposal(newProposal);
    }

    @GetMapping("/api/v1/proposal/{id}")
    private ProposalInfo oneProposal(@PathVariable String id) {
        return offerService.getProposalById(id);
    }
}
