package ua.nure.proposalservice.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ua.nure.proposalservice.dao.UserRepository;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ProposalCreationMapper {
    @Autowired
    protected VolunteerRepository volunteerRepository;
    @Autowired
    protected UserRepository userRepository;

    public void toProposal(ProposalCreation creation, HelpProposal entity) {
        if (creation == null) {
            return;
        }

        LocalDateTime actionTime = LocalDateTime.now();
        boolean isNew = entity.getId() == null;
        User actor = userRepository
                .findByLogin(((UserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUsername());

        entity.setName( creation.getName() );
        entity.setDescription( creation.getDescription() );
        entity.setLocation( creation.getLocation() );
        entity.setOwner( volunteerRepository.findById(creation.getOwnerId()).orElseThrow() );

        if(isNew) {
            entity.setId(UUID.randomUUID().toString());
            entity.setCreatedAt(actionTime);
            entity.setCreatedBy(actor.getId());
            entity.setStatus(1000);
        } else {
            entity.setModifiedAt(actionTime);
            entity.setModifiedBy(actor.getId());
        }
    }
}
