package io.greenstitch.parkinglot.model;


import io.greenstitch.parkinglot.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String registrationNumber;

    private String color;

    public Car(CarDto carDto){
        this.registrationNumber = carDto.getRegistrationNumber();
        this.color = carDto.getColor();
    }
}
