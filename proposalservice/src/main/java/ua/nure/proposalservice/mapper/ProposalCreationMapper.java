package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.model.HelpProposal;

@Mapper(componentModel = "spring")
public abstract class ProposalCreationMapper {
    @Autowired
    protected VolunteerRepository repository;

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "owner", expression = "java(repository.findById(creation.getVolunteerId()).get())")
    public abstract HelpProposal toProposal(ProposalCreation creation);
}
