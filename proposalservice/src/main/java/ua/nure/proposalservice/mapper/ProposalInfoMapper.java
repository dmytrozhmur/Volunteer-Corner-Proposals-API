package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProposalInfoMapper {
    @Autowired
    protected VolunteerInfoMapper volunteerMapper;

    @Mapping(target = "owner", expression = "java(volunteerMapper.toDto(proposal.getOwner()))")
    @Mapping(target = "createdAt", expression = "java(proposal.getCreatedAt().toString())")
    public abstract ProposalInfo toDto(HelpProposal proposal);

    public abstract List<ProposalInfo> toDtoList(List<HelpProposal> proposals);
}
