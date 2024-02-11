package org.launchcode.roomranger.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(int id) {

        super("Could not find the user with id " + id);
    }
}
