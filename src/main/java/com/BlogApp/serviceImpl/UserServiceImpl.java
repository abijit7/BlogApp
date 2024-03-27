package com.BlogApp.serviceImpl;

import com.BlogApp.Repository.UserRepo;
import com.BlogApp.entity.User;
import com.BlogApp.exception.UserNotFoundException;
import com.BlogApp.payload.UserDto;
import com.BlogApp.services.UserService;
import com.BlogApp.utli.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
private final UserRepo userRepo;
private final UserMapper userMapper;
    @Override
    public List<UserDto> findAllUser() {
         return this.userRepo.findAll().stream()
                 .map(userMapper::convertUserToUserDto).collect(Collectors.toList());

    }

    @Override
    public UserDto createUser(UserDto userdto) {
        User user = this.userMapper.convertUserDtoToUser(userdto);
        User savedUser = this.userRepo.save(user);
        return this.userMapper.convertUserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException(STR."User Not Found of Id : \{userId}"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = this.userRepo.save(user);
        return this.userMapper.convertUserToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(STR."User Not Found of Id : \{userId}"));
        return this.userMapper.convertUserToUserDto(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(STR."User Not Found of Id : \{userId}"));
         this.userRepo.delete(user);
    }

}
