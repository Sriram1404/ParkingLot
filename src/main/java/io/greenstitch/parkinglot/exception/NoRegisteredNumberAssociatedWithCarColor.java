package io.greenstitch.parkinglot.exception;

public class NoRegisteredNumberAssociatedWithCarColor extends RuntimeException{
    public NoRegisteredNumberAssociatedWithCarColor(String exceptionMessage){
        super(exceptionMessage);
    }

}
