package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dto.PhotoInfo;
import ua.nure.proposalservice.model.HelpProposalPhoto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoInfoMapper {
    @Mapping(target = "proposalId", expression = "java(photo.getHelpProposal().getId())")
    @Mapping(target = "createdAt", expression = "java(photo.getCreatedAt().toString())")
    PhotoInfo toDto(HelpProposalPhoto photo);

    List<PhotoInfo> toDtoList(List<HelpProposalPhoto> photos);
}
