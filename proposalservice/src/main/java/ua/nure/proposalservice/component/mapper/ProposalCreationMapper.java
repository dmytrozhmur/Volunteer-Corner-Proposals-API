package ua.nure.proposalservice.component.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.model.HelpProposal;

@Mapper(componentModel = "spring")
public interface ProposalCreationMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    HelpProposal toProposal(ProposalCreation creation);
}
