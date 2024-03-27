package com.BlogApp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    @NotEmpty
    @Size(min = 5,message = "Username must be min 5 character")
    private String name;
    @Email(message = "Invalid Email")
    private String email;
    @NotEmpty
    @Size(min=5,max = 15 , message = "password must be min of 5 and maximum 15 chars")
    private String password;
    @NotEmpty
    private String about;
}
