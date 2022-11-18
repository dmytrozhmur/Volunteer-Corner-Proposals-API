package ua.nure.proposalservice.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dao.UserRepository;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.model.HelpProposal;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProposalCreationMapper {
    @Autowired
    protected VolunteerRepository volunteerRepository;
    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "entity.id",
            expression = "java(entity.getId() == null ? java.util.UUID.randomUUID().toString() : entity.getId())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "owner", expression = "java(volunteerRepository.findById(creation.getOwnerId()).get())")
//    @Mapping(target = "createdBy", expression = "java(" +
//            "userRepository.findByLogin(((org.springframework.security.core.userdetails.UserDetails) " +
//            "org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())" +
//            ".getId())")
    public abstract void toProposal(ProposalCreation creation, @MappingTarget HelpProposal entity);
}
