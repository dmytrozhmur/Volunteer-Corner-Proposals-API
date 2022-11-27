package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.Volunteer;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class ProposalCreation {
    private String id;
    @NotEmpty private String name;
    @NotEmpty private String ownerId;
    @NotEmpty private String description;
    @NotEmpty private int status;
    @NotEmpty private String location;
}
