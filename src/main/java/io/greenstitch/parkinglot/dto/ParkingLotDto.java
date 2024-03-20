package io.greenstitch.parkinglot.dto;


import io.greenstitch.parkinglot.model.Address;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotDto {
   @Min(value=1,message="Proper value for capacity field expected")
    int capacity;
    //@NotNull(message = "Parking Lot address field value cannot be null")
    Address address;
}
