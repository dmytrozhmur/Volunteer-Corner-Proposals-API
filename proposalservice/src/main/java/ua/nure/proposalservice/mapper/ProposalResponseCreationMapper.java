package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dao.HelpSeekerRepository;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalResponseCreation;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.HelpProposalResponse;

@Mapper(componentModel = "spring")
public abstract class ProposalResponseCreationMapper {
    @Autowired
    protected HelpSeekerRepository seekerRepository;

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "respondent", expression = "java(" +
            "seekerRepository.findById(creation.getSeekerId()).orElseThrow())")
    public abstract HelpProposalResponse toEntity(ProposalResponseCreation creation);
}
