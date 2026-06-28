package com.acciojobs.book_my_show.controllers;

import com.acciojobs.book_my_show.dtos.HallRequestDto;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.models.Hall;
import com.acciojobs.book_my_show.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hall")
public class HallController {

    private HallService hallService;

    @Autowired
    public HallController(HallService hallService){
        this.hallService = hallService;
    }

    @PostMapping("/create")
    public ResponseEntity createHall(
            @RequestParam UUID theaterOwnerSysId,
            @RequestParam UUID theaterSysId,
            @RequestBody HallRequestDto hallRequestDto
            ){
        HashMap<String, String> exceptionMessage = new HashMap<>();
        try{
            Hall hall = hallService.createHall(theaterOwnerSysId, theaterSysId, hallRequestDto);
            return new ResponseEntity(hall, HttpStatus.CREATED);
        }catch (UnAuthorizedException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.UNAUTHORIZED);
        }catch (InvalidAttributesException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
