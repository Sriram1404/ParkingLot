package io.greenstitch.parkinglot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TicketRequestDto {

    @NotNull(message="Car registration number field value cannot be empty")
    private String registrationNumber;

    @NotNull(message="Parking slot field value cannot be empty")
    private Integer slot;
}
