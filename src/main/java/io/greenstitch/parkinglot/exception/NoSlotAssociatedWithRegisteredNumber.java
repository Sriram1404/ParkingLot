package io.greenstitch.parkinglot.exception;

public class NoSlotAssociatedWithRegisteredNumber  extends RuntimeException{
    public NoSlotAssociatedWithRegisteredNumber(String message){
        super(message);
    }
}
