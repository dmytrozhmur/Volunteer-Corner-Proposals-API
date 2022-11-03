package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "HelpProposalsPhotos")
public class HelpProposalPhoto {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @OneToOne
    @JoinColumn(name = "HelpProposalId", referencedColumnName = "Id") HelpProposal helpProposal;
    @Column(name = "FilePath") private String filePath;
    @Column(name = "CreatedBy") private String createdBy;
    @Column(name = "CreatedDate") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
}
