package com.nishantLearning.manytomany.mapper;

import com.nishantLearning.manytomany.DTO.UserDTO;
import com.nishantLearning.manytomany.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User entity);
    User toEntity(UserDTO dto);
}
