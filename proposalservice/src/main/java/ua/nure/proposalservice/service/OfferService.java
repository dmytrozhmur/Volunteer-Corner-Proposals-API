package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalInfo;

import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;
}
