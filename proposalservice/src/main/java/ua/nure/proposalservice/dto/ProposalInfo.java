package ua.nure.proposalservice.dto;

import lombok.Data;

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
