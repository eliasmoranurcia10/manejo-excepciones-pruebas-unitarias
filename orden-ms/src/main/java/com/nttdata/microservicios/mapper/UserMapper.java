package com.nttdata.microservicios.mapper;

import com.nttdata.microservicios.model.dto.user.UserDto;
import com.nttdata.microservicios.model.dto.user.UserSaveRequestDto;
import com.nttdata.microservicios.model.dto.user.UserSaveResponseDto;
import com.nttdata.microservicios.model.dto.user.UserUpdateDto;
import com.nttdata.microservicios.model.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "idUser")
    @Mapping(target = "status", source = "active")
    UserDto toUserDto(User user);
    List<UserDto> toUsersDto(List<User> users);

    @InheritInverseConfiguration
    User toUser(UserDto userDto);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    User toUserSaveRequest(UserSaveRequestDto userSaveRequestDto);

    @Mapping(target = "userId", source = "idUser")
    UserSaveResponseDto toUserSaveResponseDto(User user);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "active", source = "status")
    @Mapping(target = "pedidos", ignore = true)
    void updateEntityFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);

    // de user -> active: boolean --a--> userDto -> status: String
    @AfterMapping
    default void setRemainingValues(User user, @MappingTarget UserDto userDto) {
        userDto.setStatus(Boolean.TRUE.equals(user.getActive()) ? "Active" : "Inactive");
    }

    // de UseUpdateDto -> status: string --a--> active: boolean
    default Boolean mapStatusToActive(String status) {
        if(status == null ) return null;
        return "Active".equalsIgnoreCase(status);
    }
}
