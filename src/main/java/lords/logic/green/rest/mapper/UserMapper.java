package lords.logic.green.rest.mapper;

import lombok.Data;
import lords.logic.green.model.User;
import lords.logic.green.rest.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .lastName(user.getLastName())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
