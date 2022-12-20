package ua.nure.proposalservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProposalInfo {
    private String id;
    private List<PhotoInfo> photos;
    private String name;
    private VolunteerInfo owner;
    private String description;
    private int status;
    private String createdAt;
    private String modifiedAt;
    private String location;
}
