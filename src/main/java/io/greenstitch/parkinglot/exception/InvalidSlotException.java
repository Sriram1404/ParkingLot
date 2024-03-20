package io.greenstitch.parkinglot.exception;

public class InvalidSlotException extends RuntimeException{
    public InvalidSlotException(String exceptionMessage){
        super(exceptionMessage);
    }

}
