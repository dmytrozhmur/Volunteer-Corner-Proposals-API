package ua.nure.proposalservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "HelpProposalResponse")
public class HelpProposalResponse {
    @Id
    @Column(name = "Id") private String id;
    @ManyToOne
    @JoinColumn(name = "HelpProposalToId", referencedColumnName = "Id") HelpProposal toProposal;
    @ManyToOne
    @JoinColumn(name = "HelpSeekerFromId", referencedColumnName = "Id") HelpSeeker respondent;
    @ManyToOne
    @JoinColumn(name = "IncludedHelpRequest", referencedColumnName = "Id") HelpRequest attachment;
    @Column(name = "Comment") private String comment;
    @Column(name = "SelectedByVolunteer") private boolean selected;
}
