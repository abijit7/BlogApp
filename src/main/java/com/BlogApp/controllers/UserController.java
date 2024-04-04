package com.BlogApp.controllers;

import com.BlogApp.entity.User;
import com.BlogApp.payload.ApiResponse;
import com.BlogApp.payload.UserDto;
import com.BlogApp.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
private final UserService userService;
@GetMapping("/all-user")
public ResponseEntity<List<UserDto>> findAllUser(){
    List<UserDto> user = this.userService.findAllUser();
    return new ResponseEntity<>(user, HttpStatus.OK);
}
@PostMapping("/add-user")
public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
    UserDto user = this.userService.createUser(userDto);
    return new ResponseEntity<>(user,HttpStatus.CREATED);
}
@GetMapping("/id/{id}")
public ResponseEntity<UserDto> findById(@PathVariable("id")  Long userId){
    UserDto userDto = this.userService.getUserById(userId);
    return new ResponseEntity<>(userDto,HttpStatus.OK);
}
    @PutMapping("/update/id/{id}")
public ResponseEntity<UserDto> updateUserById(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long userId){
    UserDto userDto1 = this.userService.updateUser(userDto,userId);
    return new ResponseEntity<>(userDto1,HttpStatus.OK);
}
@DeleteMapping("/delete/id/{id}")
public ResponseEntity<ApiResponse> deleteUser( @PathVariable("id") Long userId){
    this.userService.deleteUserById(userId);
    return new ResponseEntity<>(new ApiResponse("User Deleted successfully",true),HttpStatus.OK);
}

}
