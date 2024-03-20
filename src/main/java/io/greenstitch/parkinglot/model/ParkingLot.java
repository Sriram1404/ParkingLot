package io.greenstitch.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    private Long id = System.currentTimeMillis();
    private Address address;
    private List<Slot> slots;
    private Integer size;


    public ParkingLot(Integer size,Address address) {
        this.size = size;
        this.slots = new ArrayList(size);
        this.address = address;
        initialize();
    }

    private void initialize() {
        for (int slotNumber = 1; slotNumber <= size; slotNumber++) {
            slots.add(Slot.builder()
                    .id(slotNumber)
                    .isOccupied(false)
                    .build());
        }
    }
}
