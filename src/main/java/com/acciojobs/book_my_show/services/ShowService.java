package com.acciojobs.book_my_show.services;

import com.acciojobs.book_my_show.dtos.ShowRequestDto;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.models.Hall;
import com.acciojobs.book_my_show.models.Show;
import com.acciojobs.book_my_show.models.Theater;
import com.acciojobs.book_my_show.models.User;
import com.acciojobs.book_my_show.repositories.ShowRepository;
import com.acciojobs.book_my_show.transformers.ApplicationTransformer;
import com.acciojobs.book_my_show.utilitis.SystemUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private UserService userService;
    private HallService hallService;
    private ShowRepository showRepository;
    private ApplicationTransformer applicationTransformer;

    @Autowired
    public ShowService(UserService userService,
                       HallService hallService,
                       ShowRepository showRepository,
                       ApplicationTransformer applicationTransformer){
        this.userService = userService;
        this.hallService = hallService;
        this.showRepository = showRepository;
        this.applicationTransformer = applicationTransformer;
    }

    public boolean isOverLappingShow(List<Show> shows, Long startTime, Long endTime){
        Collections.sort(shows);
        for(Show show : shows){
            if(show.getEndTimeInSeconds() >= startTime){
                return true;
            }
        }
        return false;
    }



    public Show createShow(ShowRequestDto showRequestDto,
                           UUID hallSysId,
                           UUID userSysId) throws InvalidAttributesException {
        User user = userService.verifyTheaterOwner(userSysId);
        Hall hall = hallService.verifyHallSysId(hallSysId);
        if(!hall.getTheater().getOwner().getSysId().equals(user.getSysId())){
            throw new UnAuthorizedException("User is not allowed to create show in hall");
        }
        LocalDateTime startTime = showRequestDto.getStartTime();
        Long startTimeInSeconds = SystemUtility.convertShowTimeInSeconds(startTime);
        LocalDateTime endTime = showRequestDto.getEndTime();
        Long endTimeInSeconds = SystemUtility.convertShowTimeInSeconds(endTime);
        // Now we need to identify is this show over lapping with other shows of the hall
        List<Show> shows = showRepository.findByHall(hall);
        boolean isOverLapping = this.isOverLappingShow(shows, startTimeInSeconds, endTimeInSeconds);
        if(isOverLapping){
            throw new IllegalArgumentException("Overlapping timings");
        }
        Show show = applicationTransformer.transformShowDtoToShow(showRequestDto,
                hall,
                user,
                startTimeInSeconds,
                endTimeInSeconds);

        showRepository.save(show);
        return show;
    }

}
