package ua.nure.proposalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.proposalservice.service.OfferService;

@RestController
public class OfferController {
    @Autowired
    private OfferService offerService;
}
