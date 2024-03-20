package io.greenstitch.parkinglot.controller;

import io.greenstitch.parkinglot.dto.CarDto;
import io.greenstitch.parkinglot.dto.ParkingLotDto;
import io.greenstitch.parkinglot.dto.SlotResponseDto;
import io.greenstitch.parkinglot.exception.*;
import io.greenstitch.parkinglot.model.Car;
import io.greenstitch.parkinglot.model.Ticket;
import io.greenstitch.parkinglot.service.ParkingLotService;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.greenstitch.parkinglot.response.CustomApiResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/parking-lot")
public class ParkingLotController {
    @Autowired
    ParkingLotService parkingLotService;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @PostMapping("/")
    public ResponseEntity createParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ParkingLotDto>> violations = validator.validate(parkingLotDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        parkingLotService.initialize(parkingLotDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.builder()
                        .code(200).data("Created a parking lot with " + parkingLotDto.getCapacity() + " slots ").build());
    }

    @PostMapping("/assign-parking-slot")
    public ResponseEntity assignParkingSlot(@RequestBody CarDto carDto) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CarDto>> violations = validator.validate(carDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        try {
            Ticket ticket = parkingLotService.allocateParkingSlot(new Car(carDto));
            return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.builder().code(200)
                    .data("Allocated slot number: " + ticket.getSlot().getId()).build());
        } catch (NoSlotAvailableException exception) {
            logger.error("Exception occurred: " + exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CustomApiResponse.builder().code(400).data("Sorry, parking lot is full").build());
    }

    @DeleteMapping("/release-parking-slot/{slotId}")
    public ResponseEntity releaseParkingSlot(@PathVariable Integer slotId) {
        try {
            parkingLotService.releaseParkingSlot(slotId);
            return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.builder().code(200)
                    .data("slot number: " + slotId + " is free").build());
        } catch (NoSlotAvailableException exception) {
            logger.error("Exception occurred: " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomApiResponse.builder().code(404)
                    .data(exception.getMessage()).build());

        } catch (InvalidSlotException exception) {
            logger.error("Exception occurred: " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomApiResponse.builder().code(400)
                    .data(exception.getMessage()).build());
        }
    }


    @GetMapping("/cars/color/{carColor}/registered-numbers")
    public ResponseEntity getRegisteredNumberByCarColor(@PathVariable(value = "carColor") String color) {
        Set<String> registeredNumbers = parkingLotService.getRegistrationNumberByCarColor(color);
        if (registeredNumbers.isEmpty()) {
            throw new NoRegisteredNumberAssociatedWithCarColor("Sorry, No car registered numbers is associated with given car color");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.builder().code(200).data(registeredNumbers).build());
    }


    @GetMapping("/cars/registration-number/{registeredNumber}/parking-slot")
    public ResponseEntity getParkingSlotsByRegisteredNumber(@PathVariable(value = "registeredNumber") String registeredNumber) {
        Integer slot = parkingLotService.getParkingSlotByRegistrationNumber(registeredNumber);
        if (slot == null) {
            throw new NoSlotAssociatedWithRegisteredNumber("Sorry, No slot is associated with given car registered number");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.builder().code(200).data("Parking slot number: "+slot).build());
    }

    @GetMapping("/cars/color/{carColor}/parking-slots")
    public ResponseEntity getParkingSlotByCarColor(@PathVariable(value = "carColor") String color) {
        Set<Integer> slots = parkingLotService.getParkingSlotByCarColor(color);
        if (slots.isEmpty()) {
            throw new NoSlotAssociatedWithCarColor("Sorry, No Slot is associated with given car color");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.builder().code(200).data(slots).build());
    }


    @GetMapping("/parking-slots")
    public ResponseEntity getAllOccupiedParkingSlot() {
        List<SlotResponseDto> slots = parkingLotService.fetchAllOccupiedSlot()
                .stream()
                .map(s -> new SlotResponseDto(s))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.builder().code(200).data(slots).build());
    }
}
