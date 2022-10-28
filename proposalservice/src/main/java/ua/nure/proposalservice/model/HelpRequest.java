package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "HelpRequests")
public class HelpRequest {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @OneToOne
    @JoinColumn(name = "OwnerId", referencedColumnName = "Id") HelpSeeker helpSeeker;
    @Column(name = "Name") private String name;
    @Column(name = "Description") private String description;
    @Column(name = "Status") private int status;
    @Column(name = "CreatedDate") private String createdBy;
    @Column(name = "CreatedBy") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
}
