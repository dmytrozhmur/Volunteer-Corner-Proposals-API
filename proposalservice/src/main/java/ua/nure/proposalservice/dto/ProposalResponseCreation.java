package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.HelpRequest;

import javax.validation.constraints.NotEmpty;

@Data
public class ProposalResponseCreation {
    @NotEmpty private String seekerId;
    @NotEmpty private String comment;
    private HelpRequest attachment;
}
