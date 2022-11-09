package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProposalInfoMapper {
    @Mapping(source = "proposal.owner.id", target = "ownerId")
    ProposalInfo toDto(HelpProposal proposal);

    List<ProposalInfo> toDtoList(List<HelpProposal> proposals);
}
