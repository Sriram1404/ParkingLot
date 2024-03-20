package io.greenstitch.parkinglot.repository;

import io.greenstitch.parkinglot.model.Address;
import io.greenstitch.parkinglot.model.Car;
import io.greenstitch.parkinglot.model.Slot;
import io.greenstitch.parkinglot.model.Ticket;

import java.util.List;
import java.util.Set;

public interface IParkingLotRepository {
     void initialize();
     void initialize(int capacity, Address address);
    Slot getParkingSlot();
    public void save(Slot slot);
    Slot getParkingSlotBySlotId(Integer slotId);
     Boolean releaseParkingSlot(Slot slot);
    Integer fetchParkingSlotByRegistrationNumber(String registrationNumber);
    List<Slot> fetchAll();
    Set<Integer> fetchParkingSlotByCarColor(String color);
    Set<String> fetchRegistrationNumberByCarColor(String color);
}
