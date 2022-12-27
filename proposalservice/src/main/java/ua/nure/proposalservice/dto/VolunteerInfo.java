package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.User;

@Data
public class VolunteerInfo {
    private String id;
    private UserInfo user;
    private String createdAt;
    private String modifiedAt;
    private String approved;
}
