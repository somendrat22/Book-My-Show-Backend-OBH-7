package com.acciojobs.book_my_show.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String userType;
    private String phoneNumber;
    private String address;
}
