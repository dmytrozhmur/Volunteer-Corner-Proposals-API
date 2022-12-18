package ua.nure.proposalservice.dto;

import lombok.Data;
import ua.nure.proposalservice.model.Role;

import javax.persistence.*;
import java.util.Set;

@Data
public class UserInfo {
    private String id;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
}
