package ua.nure.proposalservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.proposalservice.dto.UserInfo;
import ua.nure.proposalservice.model.User;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    @Mapping(target = "email", conditionExpression = "java(user.getPolicy() == 1000 || " +
            "!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication() " +
            "instanceof org.springframework.security.authentication.AnonymousAuthenticationToken))")
    @Mapping(target = "phone", conditionExpression = "java(user.getPolicy() == 1000 || " +
            "!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication() " +
            "instanceof org.springframework.security.authentication.AnonymousAuthenticationToken))")
    UserInfo toDto(User user);
}
