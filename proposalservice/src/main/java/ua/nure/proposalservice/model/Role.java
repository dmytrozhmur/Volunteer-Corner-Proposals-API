package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "AspNetRoles")
public class Role {
    @Id
    @Column(name = "Id") private String id;
    @Column(name = "Name") private String name;
    @Column(name = "NormalizedName") private String normalizedName;
    @Column(name = "ConcurrencyStamp") private String stamp;
}
