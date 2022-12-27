package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "Volunteers")
public class Volunteer {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @OneToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id") User user;
    @Column(name = "CreatedBy") private String createdBy;
    @Column(name = "CreatedDate") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
    @Column(name = "IsApproved") private boolean approved;
}
