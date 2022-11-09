package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "AspNetUsers")
public class User {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
//    @ManyToMany
//    @JoinTable(
//            inverseJoinColumns = @JoinColumn(name = "RoleId"),
//            joinColumns = @JoinColumn(name = "UserId"),
//            name = "AspNetUserRoles")
   // private Set<Role> roles;
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
