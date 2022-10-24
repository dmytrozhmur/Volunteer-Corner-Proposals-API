package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String surname;
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
