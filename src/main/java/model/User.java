package com.epam.library.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private UserRole userRole;

}
