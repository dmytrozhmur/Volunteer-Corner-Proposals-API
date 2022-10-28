package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Entity(name = "AspNetUsers")
public class User {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @Column(name = "FirstName") private String firstName;
    @Column(name = "LastName") private String lastName;
    @Column(name = "Patronymic") private String patronymic;
    @Column(name = "UserName") private String login;
    @Column(name = "Email") private String email;
    @Column(name = "PasswordHash") private String password;
    @Column(name = "PhoneNumber")  private String phone;
    @Column(name = "PhoneNumberConfirmed") private boolean phoneConfirmed;
    @Column(name = "TwoFactorEnabled") private boolean twoFactorEnabled;
}
