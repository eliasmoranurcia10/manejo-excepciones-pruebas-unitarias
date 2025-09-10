package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.user.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.user.UserUpdateDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", source = "idUser")
    @Mapping(target = "status", source = "active")
    UserDto map(User user);
    List<UserDto> map(List<User> users);


    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    User toEntity(UserSaveRequestDto userSaveRequestDto);

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
