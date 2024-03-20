package io.greenstitch.parkinglot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    private Integer id;
    private Boolean isOccupied;
    private Car car;
}
