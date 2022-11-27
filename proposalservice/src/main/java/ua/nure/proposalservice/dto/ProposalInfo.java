package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.Volunteer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Data
public class ProposalInfo {
    private String id;
    private String name;
    private String ownerId;
    private String description;
    private int status;
    private String createdAt;
    private String modifiedAt;
    private String location;
}
