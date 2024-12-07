package com.demo.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private long id;
    @NotEmpty                    //null and empty both the fields are not allowed here
    @Size(min=2, message = "Name should be atleast 2 characters")
    private String name;
    private String username;
    @Email
    private String emailId;
    private String password;
    private String userRole;

}
