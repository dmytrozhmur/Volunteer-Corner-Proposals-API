package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "HelpProposals")
public class HelpProposal {
    @Id
    @Column(name = "Id", nullable = false, length = 450) private String id;
    @ManyToOne
    @JoinColumn(name = "OwnerId", referencedColumnName = "Id") private Volunteer owner;
    @OneToMany(mappedBy = "helpProposal") private List<HelpProposalPhoto> photos;
    @Column(name = "Name") private String name;
    @Column(name = "Description") private String description;
    @Column(name = "Status") private int status;
    @Column(name = "CreatedBy") private String createdBy;
    @Column(name = "CreatedDate") private LocalDateTime createdAt;
    @Column(name = "LastModifiedBy") private String modifiedBy;
    @Column(name = "LastModifiedDate") private LocalDateTime modifiedAt;
    @Column(name = "Location") private String location;

    protected HelpProposal() {}

    public static HelpProposal empty() {
        return new HelpProposal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelpProposal proposal = (HelpProposal) o;
        return status == proposal.status && Objects.equals(id, proposal.id) && Objects.equals(owner, proposal.owner) && Objects.equals(name, proposal.name) && Objects.equals(description, proposal.description) && Objects.equals(location, proposal.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
