package com.traveldosth.controller;

import com.traveldosth.model.Booking;
import com.traveldosth.model.Car;
import com.traveldosth.model.dto.request.BookingRequest;

import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Customer can perform below operations.
 *      Can book a cab for required seating capacity using the service
 *      Can cancel cab which is booked by the same user
 */
@Api
public interface CustomerApi {

    @ApiOperation(value = "Get the list of available cars in the system", response = Iterable.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public List<Car> getAvailableCars();

    @ApiOperation(value = "Book a car", response = Booking.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public Booking bookCar(@RequestBody BookingRequest bookingRequest);

    @ApiOperation(value = "Cancel a booking", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String cancelBooking(@RequestBody Long bookingId);
}
