package ua.nure.proposalservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.service.OfferService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceGettingTest {
    @Autowired
    private OfferService offerService;
    @MockBean
    private OfferRepository offerRepository;

    private List<HelpProposal> proposals = new ArrayList<>();

    @BeforeEach
    public void mockProposals() {

        HelpProposal proposal_1 = HelpProposal.empty();
        proposal_1.setId("54523");
        proposal_1.setCreatedAt(LocalDateTime.now());

        HelpProposal proposal_2 = HelpProposal.empty();
        proposal_2.setId("75412");
        proposal_2.setCreatedAt(LocalDateTime.now());

        HelpProposal proposal_3 = HelpProposal.empty();
        proposal_3.setId("75235");
        proposal_3.setCreatedAt(LocalDateTime.now());

        proposals.addAll(Arrays.asList(proposal_1, proposal_2, proposal_3));

        when(offerRepository.findAll()).thenReturn(proposals);
    }

    @Test
    public void getAllProposals() {
        List<ProposalInfo> expected = Arrays.asList(new ProposalInfo(), new ProposalInfo(), new ProposalInfo());
        for(int i = 0; i < proposals.size(); i++) {
            expected.get(i).setId(proposals.get(i).getId());
            expected.get(i).setCreatedAt(String.valueOf(proposals.get(i).getCreatedAt()));
        }
        List<ProposalInfo> actual = offerService.getAllProposals();

        assertThat(actual, is(expected));
        verify(offerRepository).findAll();
    }
}
