package org.launchcode.roomranger.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){

        super("Cannot find " + message);
    }
}
