package com.traveldosth.controller;

import com.traveldosth.core.exception.InvalidOperationException;
import com.traveldosth.core.exception.ResourceNotFoundException;
import com.traveldosth.model.Booking;
import com.traveldosth.model.Car;
import com.traveldosth.model.dto.request.BookingRequest;
import com.traveldosth.service.BookingService;
import com.traveldosth.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Api(value = "/customer", tags = "Customer Services", description = "Book a cars, cancel a booking, view available cars")
@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerController implements CustomerApi {

    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    @Override
    @GetMapping("/get-available-cars")
    public List<Car> getAvailableCars() {
        log.info("Getting list of available cars");
        return carService.getAvailableCars();
    }

    @Override
    @PostMapping("/book-car")
    public Booking bookCar(@RequestBody BookingRequest bookingRequest) {
        Long userId = 1L;  // TO DO. get driverId from JWT
        Long carId = bookingRequest.getCarId();
        log.info("User with userId: {} is booking the car with carId: {}", userId, carId);
        Car car = carService.findCarById(carId);
        if(car == null) {
            throw new ResourceNotFoundException("Car with carId: "+carId+" not found in the system.");
        }
        Booking booking = bookingService.bookCar(userId, car, bookingRequest.getStartLocation(), bookingRequest.getEndLocation());
        return booking;
    }

    @Override
    @PutMapping("/cancel-booking")
    public String cancelBooking(@RequestBody Long bookingId) {
        Long userId = 1L;   // TO DO. get driverId from JWT
        log.info("User with userId: {} is cancelling the booking with bookingId: {}", userId, bookingId);
        Booking booking = bookingService.findBookingById(bookingId);
        if(booking == null) {
            throw new ResourceNotFoundException("Booking with bookingId: "+bookingId+" not found in the system.");
        }
        if(booking.getUserId().compareTo(userId) != 0) {
            throw new InvalidOperationException("You are not allowed to cancel bookings of other users.");
        }
        bookingService.cancelBooking(booking);
        return "Successfully cancelled the booking.";
    }

}
