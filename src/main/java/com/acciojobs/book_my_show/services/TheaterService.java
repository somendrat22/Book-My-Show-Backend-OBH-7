package com.acciojobs.book_my_show.services;

import com.acciojobs.book_my_show.dtos.TheaterRequestDto;
import com.acciojobs.book_my_show.enums.UserType;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.exceptions.UserNotFoundException;
import com.acciojobs.book_my_show.models.Theater;
import com.acciojobs.book_my_show.models.User;
import com.acciojobs.book_my_show.repositories.TheaterRepository;
import com.acciojobs.book_my_show.repositories.UserRepository;
import com.acciojobs.book_my_show.transformers.ApplicationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TheaterService {

    private UserRepository userRepository;
    private ApplicationTransformer applicationTransformer;
    private TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(UserRepository userRepository,
                          ApplicationTransformer applicationTransformer,
                          TheaterRepository theaterRepository){
        this.userRepository = userRepository;
        this.theaterRepository = theaterRepository;
        this.applicationTransformer = applicationTransformer;
    }


    public Theater createTheater(TheaterRequestDto theaterRequestDto,
                                 UUID userSysId){
        // With the help of this sysId i need to get the user obejct
        User user = userRepository.findById(userSysId).orElse(null);
        if(user == null){
            throw new UserNotFoundException(String.format("user with id %s does not exist", userSysId.toString()));
        }
        if(!user.getUserType().equals(UserType.THEATER_OWNER.toString())){
            throw new UnAuthorizedException("User is not allowed create theater");
        }
        // Adpater to transform the request and return the Theater model
        Theater theater  = applicationTransformer.transformTheaterRequestToTheaterModel(theaterRequestDto,
                user);
        // save this theater inside the theater table
        theaterRepository.save(theater);
        return theater;

    }

}
