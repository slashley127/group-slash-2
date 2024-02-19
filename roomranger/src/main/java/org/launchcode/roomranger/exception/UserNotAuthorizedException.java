package org.launchcode.roomranger.exception;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(String userName){

        super("User not authorized " + userName);
    }
}
