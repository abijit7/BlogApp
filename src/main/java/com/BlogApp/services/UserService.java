package com.BlogApp.services;

import com.BlogApp.payload.UserDto;

import java.util.List;

public interface UserService {


     List<UserDto> findAllUser();
     UserDto createUser(UserDto userdto);
     UserDto updateUser(UserDto userDto,Long userId);
     UserDto getUserById(Long userId);
     void deleteUserById(Long userId);

}
