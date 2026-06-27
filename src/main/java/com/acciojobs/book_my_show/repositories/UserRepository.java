package com.acciojobs.book_my_show.repositories;

import com.acciojobs.book_my_show.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {

    private HashMap<String, User> userDB;

    public UserRepository(){
        this.userDB = new HashMap<>();
    }

    public User save(User user){
        this.userDB.put(user.getUserId(), user);
        return user;
    }


}
