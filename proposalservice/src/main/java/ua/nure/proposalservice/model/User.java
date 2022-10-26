package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity(name = "AspNetUsers")
public class User {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String email;
    private String password;
    private String phone;
    private boolean blocked;
    private boolean confirmed;
    private List<Role> roles;
    private String organizationId;
}
