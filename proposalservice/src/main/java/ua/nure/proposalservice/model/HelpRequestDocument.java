package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "HelpProposalsDocuments")
public class HelpRequestDocument {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @OneToOne
    @JoinColumn(name = "UserRequestId", referencedColumnName = "Id") HelpRequest helpRequest;
    @Column(name = "FilePath") private String filePath;
    @Column(name = "CreatedBy") private String createdBy;
    @Column(name = "CreatedDate") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
}
