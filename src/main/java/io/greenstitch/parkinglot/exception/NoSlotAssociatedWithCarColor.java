package io.greenstitch.parkinglot.exception;

public class NoSlotAssociatedWithCarColor extends RuntimeException{
    public NoSlotAssociatedWithCarColor(String message){
        super(message);
    }
}
