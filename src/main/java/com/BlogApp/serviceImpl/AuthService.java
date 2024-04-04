package com.BlogApp.serviceImpl;

import com.BlogApp.Repository.UserRepo;
import com.BlogApp.entity.User;
import com.BlogApp.payload.AuthResponse;
import com.BlogApp.payload.LoginRequest;
import com.BlogApp.payload.RegisterRequest;
import com.BlogApp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager ;
    public AuthResponse register (RegisterRequest registerRequest){
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserRole(registerRequest.getUserRole());
        user = userRepo.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
    public AuthResponse login (LoginRequest loginRequest){
     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
     User user = this.userRepo.findByEmail(loginRequest.getEmail());
     String token = jwtService.generateToken(user);
return new AuthResponse(token);
    }
}
