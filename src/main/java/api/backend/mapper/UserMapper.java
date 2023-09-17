package api.backend.mapper;

import api.backend.dto.SignUpRequest;
import api.backend.dto.UserDTO;
import api.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    @Mapping(target = "password",ignore = true)
    User signUpToUser(SignUpRequest request);
}
