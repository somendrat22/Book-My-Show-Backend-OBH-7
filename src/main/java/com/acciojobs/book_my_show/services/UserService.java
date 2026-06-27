package com.acciojobs.book_my_show.services;

import com.acciojobs.book_my_show.dtos.UserRequestDto;
import com.acciojobs.book_my_show.enums.UserType;
import com.acciojobs.book_my_show.models.User;
import com.acciojobs.book_my_show.repositories.UserRepository;
import com.acciojobs.book_my_show.transformers.ApplicationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ApplicationTransformer applicationTransformer;
    private UserRepository userRepository;

    @Autowired
    public UserService(ApplicationTransformer applicationTransformer,
                       UserRepository userRepository){
        this.applicationTransformer = applicationTransformer;
        this.userRepository = userRepository;
    }

    public User registerOwner(UserRequestDto userRequestDto){
        User user = applicationTransformer.transformUserRequestDtoToUserModel(userRequestDto, UserType.THEATER_OWNER.toString());
        this.userRepository.save(user);
        return user;

    }

    public User registerCustomer(UserRequestDto userRequestDto){
        User user = applicationTransformer.transformUserRequestDtoToUserModel(userRequestDto, UserType.CUSTOMER.toString());
        this.userRepository.save(user);
        return user;
    }


}
