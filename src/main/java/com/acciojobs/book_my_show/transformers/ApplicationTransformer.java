package com.acciojobs.book_my_show.transformers;

import com.acciojobs.book_my_show.dtos.HallRequestDto;
import com.acciojobs.book_my_show.dtos.ShowRequestDto;
import com.acciojobs.book_my_show.dtos.TheaterRequestDto;
import com.acciojobs.book_my_show.dtos.UserRequestDto;
import com.acciojobs.book_my_show.models.Hall;
import com.acciojobs.book_my_show.models.Show;
import com.acciojobs.book_my_show.models.Theater;
import com.acciojobs.book_my_show.models.User;
import com.acciojobs.book_my_show.services.HallService;
import com.acciojobs.book_my_show.utilitis.SystemUtility;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class ApplicationTransformer {

    public User transformUserRequestDtoToUserModel(UserRequestDto userRequestDto, String userType){
        return User.builder()
                .userId(SystemUtility.generate("USER"))
                .userType(userType)
                .fullName(userRequestDto.getFullName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy("system")
                .updatedBy("system")
                .phoneNumber(userRequestDto.getPhoneNumber())
                .address(userRequestDto.getAddress())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
    }

    public Theater transformTheaterRequestToTheaterModel(TheaterRequestDto theaterRequestDto,
                                                         User user){
        return Theater.builder()
                .halls(new ArrayList<>())
                .owner(user)
                .city(theaterRequestDto.getCity())
                .theaterName(theaterRequestDto.getTheaterName())
                .country(theaterRequestDto.getCountry())
                .State(theaterRequestDto.getState())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(user.getEmail())
                .updatedBy(user.getEmail())
                .build();
    }

    public Hall transformDtoToHallModel(
            HallRequestDto hallRequestDto,
            Theater theater
    ){
        return Hall.builder()
                .hallId(SystemUtility.generate("HALL"))
                .hallName(hallRequestDto.getHallName())
                .theater(theater)
                .seatCapacity(hallRequestDto.getSeatCapacityPerRow())
                .rowRange(hallRequestDto.getRowRange())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy(theater.getOwner().getEmail())
                .updatedBy(theater.getOwner().getEmail())
                .build();
    }

    public Show transformShowDtoToShow(
            ShowRequestDto showRequestDto,
            Hall hall,
            User user,
            Long startTimeInSeconds,
            Long endTimeInSeconds
    ){
        return Show.builder()
                .hall(hall)
                .showId(SystemUtility.generate("SHOW"))
                .endTimeInSeconds(endTimeInSeconds)
                .startTimeInSeconds(startTimeInSeconds)
                .showPrice(showRequestDto.getShowPrice())
                .startTime(showRequestDto.getStartTime())
                .endTime(showRequestDto.getEndTime())
                .createdBy(user.getEmail())
                .updatedBy(user.getEmail())
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
