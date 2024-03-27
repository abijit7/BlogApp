package com.BlogApp.utli;

import com.BlogApp.entity.User;
import com.BlogApp.payload.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    public User convertUserDtoToUser(UserDto userDto){
        User user = new User();
        user = modelMapper.map(userDto, User.class);
        return user;
    }

}
