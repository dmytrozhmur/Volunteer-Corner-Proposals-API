package ua.nure.proposalservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.nure.proposalservice.controller.OfferController;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.mapper.ProposalCreationMapper;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.User;
import ua.nure.proposalservice.model.Volunteer;
import ua.nure.proposalservice.service.OfferService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    @Autowired
    private OfferService offerService;
    @MockBean
    private OfferRepository offerRepository;
    @MockBean
    private ProposalCreationMapper creationMapper;

    @Test
    public void createNewProposalAndGetById() {
        LocalDateTime testDate = LocalDateTime.now();
        Volunteer owner = new Volunteer();
        owner.setId("34343");
        String testId = "123A";

        ProposalInfo expected = new ProposalInfo();
        expected.setId(testId);
        expected.setCreatedAt(testDate.toString());
        expected.setOwnerId(owner.getId());

        HelpProposal created = new HelpProposal();
        created.setId(testId);
        created.setOwner(owner);
        created.setCreatedAt(testDate);

        ProposalCreation creation = new ProposalCreation();
        creation.setVolunteerId(owner.getId());

        doAnswer(invocation -> {
            HelpProposal proposal = invocation.getArgument(0);
            doReturn(Optional.of(proposal)).when(offerRepository).findById(proposal.getId());
            return null;
        }).when(offerRepository).save(any());
        doReturn(created).when(creationMapper).toProposal(creation);

        offerService.addProposal(creation);
        ProposalInfo actual = offerService.getProposalById(testId);

        assertThat(actual, is(expected));
    }
}
