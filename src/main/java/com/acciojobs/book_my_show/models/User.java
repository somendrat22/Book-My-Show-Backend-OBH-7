package com.acciojobs.book_my_show.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// {fullname, email, password, }
public class User {
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private String userType;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
