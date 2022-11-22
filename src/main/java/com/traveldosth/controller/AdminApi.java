package com.traveldosth.controller;

import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.model.DriverCarRequest;

import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * Admin can perform below operations
 *      add/delete cars to the system
 *      add/delete drivers to the system
 *      assign/re-assign cars to the drivers
 */
@Api
public interface AdminApi {

    @ApiOperation(value = "Add a car into the System", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String addCar(@RequestBody Car car);

    @ApiOperation(value = "Remove a car from the System", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String removeCar(@RequestBody Long carId);

    @ApiOperation(value = "Verify a Driver in the System", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String verifyDriver(@RequestBody Long driverId);

    @ApiOperation(value = "Remove a Driver from the System", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String removeDriver(@RequestBody Long driverId);

    @ApiOperation(value = "Assign a Car to a Driver", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public String assignCar(Long carId, Long driverId);

    @ApiOperation(value = "Get the List of Cars present in the System", response = Iterable.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public List<Car> getCars();

    @ApiOperation(value = "Get the List of Drivers present in the System", response = Iterable.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public List<Driver> getDrivers();

    @ApiOperation(value = "Get the List of Car Requests made by the Drivers", response = Iterable.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public List<DriverCarRequest> getDriverRequests();
}
