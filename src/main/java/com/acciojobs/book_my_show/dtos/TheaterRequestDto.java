package com.acciojobs.book_my_show.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRequestDto {
    private String theaterName;
    private String city;
    private String State;
    private String country;
    private String address;
}
