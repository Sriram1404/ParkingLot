package io.greenstitch.parkinglot.dto;

import io.greenstitch.parkinglot.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private SlotDto slotDto;
    private LocalDateTime inTime;
}
