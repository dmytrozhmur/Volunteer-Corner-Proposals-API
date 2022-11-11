package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dao.UserRepository;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.model.HelpProposal;

@Mapper(componentModel = "spring")
public abstract class ProposalCreationMapper {
    @Autowired
    protected VolunteerRepository volunteerRepository;
    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "owner", expression = "java(volunteerRepository.findById(creation.getVolunteerId()).get())")
    @Mapping(target = "createdBy", expression = "java(" +
            "userRepository.findByLogin(((org.springframework.security.core.userdetails.UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername()).getId()" +
            ")")
    public abstract HelpProposal toProposal(ProposalCreation creation);
}
