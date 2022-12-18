package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.proposalservice.dto.VolunteerInfo;
import ua.nure.proposalservice.model.Volunteer;

@Mapper(componentModel = "spring")
public abstract class VolunteerInfoMapper {
    @Autowired
    protected UserInfoMapper userMapper;

    @Mapping(target = "user", expression = "java(userMapper.toDto(volunteer.getUser()))")
    @Mapping(target = "createdAt", expression = "java(volunteer.getCreatedAt().toString())")
    public abstract VolunteerInfo toDto(Volunteer volunteer);
}
