package com.acciojobs.book_my_show.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequestDto {
    private Double showPrice;
    private String movieName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
