package io.greenstitch.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long id = System.currentTimeMillis();
    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private Slot slot;
}
