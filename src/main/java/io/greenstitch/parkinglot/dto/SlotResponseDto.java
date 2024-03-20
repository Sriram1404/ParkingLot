package io.greenstitch.parkinglot.dto;

import io.greenstitch.parkinglot.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotResponseDto {
    private Integer slotId;
    private String registeredNumber;
    private String color;

    public SlotResponseDto(Slot slot) {
        this.slotId = slot.getId();
        this.registeredNumber = slot.getCar().getRegistrationNumber();
        this.color = slot.getCar().getColor();
    }
}
