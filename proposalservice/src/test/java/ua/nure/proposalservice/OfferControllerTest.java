package ua.nure.proposalservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.nure.proposalservice.controller.OfferController;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.service.OfferService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OfferControllerTest {
    @Autowired
    private OfferController offerController;
    @MockBean
    private OfferService offerService;

    @Test
    public void createNewProposalAndGetItBack() {
        ProposalInfo expected = new ProposalInfo();
        expected.setId("1");
        expected.setName("Vin");
        expected.setCreatedAt("24 February 2022");
        expected.setDescription("I like NURE!");
        expected.setStatus(2000);
        expected.setOwnerId("232323");

        ProposalCreation proposalCreation = new ProposalCreation();
        proposalCreation.setDescription("I like NURE!");
        proposalCreation.setName("Vin");
        proposalCreation.setOwnerId("34343");

        when(offerService.addProposal(proposalCreation)).thenReturn(expected);

        ProposalInfo actual = offerController.newProposal(proposalCreation);

        assertThat(actual, is(expected));
}}
