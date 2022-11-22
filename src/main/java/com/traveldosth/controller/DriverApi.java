package com.traveldosth.controller;

import com.traveldosth.model.Car;

import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Driver can perform below operations.
 *      Can request for new car
 *      Can withdraw from the system
 */
@Api
public interface DriverApi {

    @ApiOperation(value = "Get the list of unassigned cars in the system", response = Iterable.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public List<Car> getUnassignedCars();

    @ApiOperation(value = "Request for a car", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String requestCar(@RequestParam Long carId);

    @ApiOperation(value = "Withdraw from the system", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String withdrawSystem();

}
