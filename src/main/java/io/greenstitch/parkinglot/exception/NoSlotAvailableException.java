package io.greenstitch.parkinglot.exception;

public class NoSlotAvailableException extends RuntimeException{
    public NoSlotAvailableException(String exceptionMessage){
        super(exceptionMessage);
    }

}
