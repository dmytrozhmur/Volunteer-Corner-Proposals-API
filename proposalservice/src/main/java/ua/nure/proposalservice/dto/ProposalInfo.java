package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.Volunteer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Data
public class ProposalInfo {
    private String id;
    private String ownerId;
    private String description;
    private int status;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
