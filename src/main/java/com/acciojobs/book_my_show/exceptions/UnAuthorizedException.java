package com.acciojobs.book_my_show.exceptions;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
       super(message);
    }
}
