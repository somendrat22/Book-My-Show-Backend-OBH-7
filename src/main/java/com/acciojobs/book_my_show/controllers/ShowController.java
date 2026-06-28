package com.acciojobs.book_my_show.controllers;

import com.acciojobs.book_my_show.dtos.ShowRequestDto;
import com.acciojobs.book_my_show.exceptions.UnAuthorizedException;
import com.acciojobs.book_my_show.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/show")
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @PostMapping("/create")
    public ResponseEntity createShow(
            @RequestParam UUID hallSysId,
            @RequestParam UUID userSysId,
            @RequestBody ShowRequestDto showRequestDto
            ){
        HashMap<String, String> exceptionMessage = new HashMap<>();
        try{
            return new ResponseEntity(showService.createShow(showRequestDto, hallSysId, userSysId), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
        }catch (InvalidAttributesException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST);
        }catch (UnAuthorizedException e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            exceptionMessage.put("message", e.getMessage());
            return new ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
