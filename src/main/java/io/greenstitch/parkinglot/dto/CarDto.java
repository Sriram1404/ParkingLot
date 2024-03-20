package io.greenstitch.parkinglot.dto;

import io.greenstitch.parkinglot.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private String registrationNumber;

    private String color;

    public CarDto(Car car) {
        this.registrationNumber=car.getRegistrationNumber();
        this.color=car.getColor();
    }
}
