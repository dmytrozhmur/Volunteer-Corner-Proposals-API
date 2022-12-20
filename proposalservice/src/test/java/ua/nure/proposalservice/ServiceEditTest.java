package ua.nure.proposalservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.nure.proposalservice.controller.OfferController;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.Volunteer;
import ua.nure.proposalservice.service.OfferService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceEditTest {

    private MockMvc mockMvc;

    @Autowired
    private OfferService offerService;

    @MockBean
    private OfferRepository offerRepository;

    private static LocalDateTime testDate = LocalDateTime.now();
    private static Volunteer testOwner = new Volunteer();
    public static HelpProposal PROPOSAL_1 = new HelpProposal();

    public void setAllData() {
        testOwner.setId("34343");

        PROPOSAL_1.setId("12345");
        PROPOSAL_1.setName("New proposal #1");
        PROPOSAL_1.setDescription("New description #1");
        PROPOSAL_1.setOwner(testOwner);
        PROPOSAL_1.setCreatedAt(testDate);

    }

    @BeforeEach
    public void setUp() {
        setAllData();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(offerService).build();
    }

    @Test
    public void editProposal() throws Exception {

        String updatedName = "Updated proposal #1";
        String updatedDescription = "Updated description #1";

        ProposalCreation proposalCreation = new ProposalCreation();
        proposalCreation.setId(PROPOSAL_1.getId());
        proposalCreation.setName(PROPOSAL_1.getName());
        proposalCreation.setDescription(PROPOSAL_1.getDescription());
        proposalCreation.setOwnerId(testOwner.getId());

        offerService.addProposal(proposalCreation);

        ProposalCreation updatedCreation = new ProposalCreation();
        updatedCreation.setId(PROPOSAL_1.getId());
        updatedCreation.setName(updatedName);
        updatedCreation.setDescription(updatedDescription);
        updatedCreation.setOwnerId(testOwner.getId());

        ProposalInfo updatedProposal = offerService.updateProposalById(PROPOSAL_1.getId(), updatedCreation);

        HelpProposal updatedHelpProposal = new HelpProposal();
        updatedHelpProposal.setId(updatedProposal.getId());
        updatedHelpProposal.setName(updatedProposal.getName());
        updatedHelpProposal.setDescription(updatedProposal.getDescription());
        updatedHelpProposal.setOwner(testOwner);

        verify(offerRepository, times(1)).save(updatedHelpProposal);
    }
}
