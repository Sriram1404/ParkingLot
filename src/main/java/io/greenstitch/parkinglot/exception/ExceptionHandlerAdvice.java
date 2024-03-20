package io.greenstitch.parkinglot.exception;

import org.springframework.http.ResponseEntity;
import io.greenstitch.parkinglot.response.CustomApiResponse;
import io.greenstitch.parkinglot.response.ValidationErrorResponse;
import io.greenstitch.parkinglot.response.Violation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ErrorHandlingControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.setCode(400);
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(NoSlotAssociatedWithRegisteredNumber.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity onNoSlotAssociatedWithRegisteredNumber(NoSlotAssociatedWithRegisteredNumber e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomApiResponse.builder().code(404).data(e.getMessage()).build());
    }

    @ExceptionHandler(NoSlotAssociatedWithCarColor.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity onNoSlotAssociatedWithCarColor(NoSlotAssociatedWithCarColor e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomApiResponse.builder().code(404).data(e.getMessage()).build());
    }

    @ExceptionHandler(InvalidSlotException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity onInvalidSlotException(InvalidSlotException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CustomApiResponse.builder().code(404).data(e.getMessage()).build());
    }
}
