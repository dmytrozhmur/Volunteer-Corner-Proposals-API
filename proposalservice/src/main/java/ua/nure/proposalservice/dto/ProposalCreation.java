package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.Volunteer;

import java.time.LocalDateTime;

@Data
public class ProposalCreation {
    private Volunteer volunteer;
    private String description;
    private int status;
}
