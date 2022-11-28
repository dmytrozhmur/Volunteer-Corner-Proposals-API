package ua.nure.proposalservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.nure.proposalservice.controller.OfferController;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.Volunteer;
import ua.nure.proposalservice.service.OfferService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ControllerEditTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private OfferService offerService;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferController offerController;

    private static LocalDateTime testDate = LocalDateTime.now();
    private static Volunteer testOwner = new Volunteer();

    public static ProposalInfo PROPOSAL_1 = new ProposalInfo();

    public static ProposalInfo PROPOSAL_2 = new ProposalInfo();

    public static ProposalInfo PROPOSAL_3 = new ProposalInfo();

    public void setAllData() {
        testOwner.setId("34343");

        PROPOSAL_1.setId("12345");
        PROPOSAL_1.setName("New proposal #1");
        PROPOSAL_1.setDescription("New description #1");
        PROPOSAL_1.setOwnerId(testOwner.getId());
        PROPOSAL_1.setCreatedAt(String.valueOf(testDate));

        PROPOSAL_2.setId("54687");
        PROPOSAL_2.setName("New proposal #2");
        PROPOSAL_2.setDescription("New description #2");
        PROPOSAL_2.setOwnerId(testOwner.getId());
        PROPOSAL_2.setCreatedAt(String.valueOf(testDate));

        PROPOSAL_3.setId("55236");
        PROPOSAL_3.setName("New proposal #3");
        PROPOSAL_3.setDescription("New description #3");
        PROPOSAL_3.setOwnerId(testOwner.getId());
        PROPOSAL_3.setCreatedAt(String.valueOf(testDate));
    }

    @BeforeEach
    public void setUp() {
        setAllData();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(offerController).build();
    }

    @Test
    public void editProposal() throws Exception {
        List<ProposalInfo> proposals = new ArrayList<>(Arrays.asList(PROPOSAL_1, PROPOSAL_2, PROPOSAL_3));

        HelpProposal updatedProposal = new HelpProposal();
        String updatedName = "Updated proposal #1";
        String updatedDescription = "Updated description #1";
        updatedProposal.setId(PROPOSAL_1.getId());
        updatedProposal.setName(updatedName);
        updatedProposal.setDescription(updatedDescription);
        updatedProposal.setOwner(testOwner);

        //LocalDateTime doesn't work

        //updatedProposal.setCreatedAt(LocalDateTime.parse(PROPOSAL_1.getCreatedAt()));
        //updatedProposal.setModifiedAt(LocalDateTime.now());

        Mockito.when(offerService.getProposalById(PROPOSAL_1.getId())).thenReturn(proposals.get(proposals.indexOf(PROPOSAL_1)));
        Mockito.when(offerRepository.save(updatedProposal)).thenReturn(updatedProposal);

        String updatedContent = objectWriter.writeValueAsString(updatedProposal);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/proposals")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        //Error 405

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated proposal #1"));
    }
}
