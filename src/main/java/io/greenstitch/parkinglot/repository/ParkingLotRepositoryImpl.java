package io.greenstitch.parkinglot.repository;

import io.greenstitch.parkinglot.dto.TicketRequestDto;
import io.greenstitch.parkinglot.exception.NoSlotAvailableException;
import io.greenstitch.parkinglot.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ParkingLotRepositoryImpl implements IParkingLotRepository {
    private ParkingLot parkingLot;
    private Map<String, Set<String>> colorToRegistrationNumberMap;
    private Map<String, Integer> regisrationNumberToSlotMap;
    private Map<String, Set<Integer>> colorToSlotMap;

    private Map<Integer, Slot> slotIdToSlotMap;

    @Override
    public void initialize() {
        this.parkingLot = new ParkingLot(0, null);
        this.colorToRegistrationNumberMap = new HashMap();
        this.regisrationNumberToSlotMap = new HashMap();
        this.colorToSlotMap = new HashMap();
        this.slotIdToSlotMap = new HashMap();
    }

    @Override
    public void initialize(int capacity, Address address) {
        this.parkingLot = new ParkingLot(capacity, address);
    }

    @Override
    public Slot getParkingSlot() {
        Slot actualSlot = null;
        for (Slot slot : parkingLot.getSlots()) {
            if (!slot.getIsOccupied()) {
                actualSlot = slot;
                break;
            }
        }
        return actualSlot;
    }

    @Override
    public Slot getParkingSlotBySlotId(Integer slotId) {
        return slotIdToSlotMap.getOrDefault(slotId, null);
    }

    @Override
    public void save(Slot slot) {
        Car car = slot.getCar();
        if (!colorToSlotMap.containsKey(car.getColor())) {
            colorToSlotMap.put(car.getColor(), new HashSet<>());
        }
        if (!colorToRegistrationNumberMap.containsKey(car.getColor())) {
            colorToRegistrationNumberMap.put(car.getColor(), new HashSet<>());
        }
        colorToSlotMap.get(car.getColor()).add(slot.getId());
        colorToRegistrationNumberMap.get(car.getColor()).add(car.getRegistrationNumber());
        regisrationNumberToSlotMap.put(car.getRegistrationNumber(), slot.getId());
        slotIdToSlotMap.put(slot.getId(), slot);
    }

    @Override
    public Boolean releaseParkingSlot(Slot slot) {
        Car car = slot.getCar();
        Integer slotId = regisrationNumberToSlotMap.remove(car.getRegistrationNumber());
        colorToSlotMap.get(car.getColor()).remove(slotId);
        colorToRegistrationNumberMap.get(car.getColor()).remove(car.getRegistrationNumber());
        slot.setIsOccupied(false);
        slot.setCar(null);
        return true;
    }

    @Override
    public Integer fetchParkingSlotByRegistrationNumber(String registrationNumber) {
        return regisrationNumberToSlotMap.getOrDefault(registrationNumber, null);
    }

    @Override
    public List<Slot> fetchAll() {
        List<Slot> parkingSlots = new ArrayList<>();
        for (Slot slot : parkingLot.getSlots()) {
            if (slot.getIsOccupied()) {
                parkingSlots.add(slot);
            }
        }
        return parkingSlots;
    }

    @Override
    public Set<Integer> fetchParkingSlotByCarColor(String color) {
        return colorToSlotMap.getOrDefault(color, new HashSet<>());
    }

    @Override
    public Set<String> fetchRegistrationNumberByCarColor(String color) {
        return colorToRegistrationNumberMap.getOrDefault(color, null);
    }
}
