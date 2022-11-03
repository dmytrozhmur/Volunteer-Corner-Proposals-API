package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "HelpProposals")
public class HelpProposal {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @ManyToOne
    @JoinColumn(name = "OwnerId", referencedColumnName = "Id") Volunteer owner;
    @Column(name = "Description") private String description;
    @Column(name = "Status") private int status;
    @Column(name = "CreatedBy") private String createdBy;
    @Column(name = "CreatedDate") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
}
