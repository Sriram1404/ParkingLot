package io.greenstitch.parkinglot.service;

import io.greenstitch.parkinglot.dto.ParkingLotDto;
import io.greenstitch.parkinglot.dto.TicketRequestDto;
import io.greenstitch.parkinglot.exception.InvalidSlotException;
import io.greenstitch.parkinglot.exception.NoSlotAvailableException;
import io.greenstitch.parkinglot.model.*;
import io.greenstitch.parkinglot.repository.IParkingLotRepository;
import io.greenstitch.parkinglot.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingLotService {
    private IParkingLotRepository parkingLotRepository;
    private ITicketRepository ticketRepository;

    @Autowired
    public ParkingLotService(IParkingLotRepository parkingLotRepository,
                             ITicketRepository ticketRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
        parkingLotRepository.initialize();

    }

    public void initialize(ParkingLotDto parkingLotDto) {
        parkingLotRepository.initialize(parkingLotDto.getCapacity(), parkingLotDto.getAddress());
    }

    public Ticket allocateParkingSlot(Car car) {
        Slot slot = parkingLotRepository.getParkingSlot();
        if (slot == null) {
            throw new NoSlotAvailableException("No slot available");
        }
        slot.setIsOccupied(true);
        slot.setCar(car);
        parkingLotRepository.save(slot);

        Ticket ticket = Ticket.builder()
                .slot(slot)
                .inTime(LocalDateTime.now())
                .build();
        ticketRepository.save(ticket);
        return ticket;
    }


    public Boolean releaseParkingSlot(Integer slotId) {
        Slot slot = parkingLotRepository.getParkingSlotBySlotId(slotId);
        if (slot == null) {
            throw new NoSlotAvailableException("Sorry, parking slot doesn't exist");
        }
        ticketRepository.remove(slotId);
//        Ticket ticket=ticketRepository.findBySlot(ticketRequestDto.getId());
//                ticket.setOutTime(LocalDateTime.now());
        return parkingLotRepository.releaseParkingSlot(slot);
    }


    public Integer getParkingSlotByRegistrationNumber(String registrationNumber) {
        return parkingLotRepository.fetchParkingSlotByRegistrationNumber(registrationNumber);
    }

    public List<Slot> fetchAllOccupiedSlot() {
        return parkingLotRepository.fetchAll();
    }

    public Set<Integer> getParkingSlotByCarColor(String color) {
        return parkingLotRepository.fetchParkingSlotByCarColor(color);
    }

    public Set<String> getRegistrationNumberByCarColor(String color) {
        return parkingLotRepository.fetchRegistrationNumberByCarColor(color);
    }

}
