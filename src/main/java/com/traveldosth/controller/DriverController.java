package com.traveldosth.controller;

import com.traveldosth.core.exception.ResourceNotFoundException;
import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.service.CarService;
import com.traveldosth.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Api(value = "/driver", tags = "Driver Services", description = "Request for a car, withdraw from the system")
@RestController
@RequestMapping("/driver")
@PreAuthorize("hasRole('ROLE_DRIVER')")
public class DriverController implements DriverApi {

    @Autowired
    private DriverService driverService;
    @Autowired
    private CarService carService;

    @Override
    @GetMapping("/get-unassigned-cars")
    public List<Car> getUnassignedCars() {
        log.info("Getting list of unassigned cars");
        return carService.getUnassignedCars();
    }

    @Override
    @PostMapping("/request-car")
    public String requestCar(@RequestParam Long carId) {
        Long driverId = 1L;  // TO DO. get driverId from JWT
        log.info("Driver with driverId: {} has requested for Car with carId: {}", driverId, carId);
        Driver driver = driverService.findDriverById(driverId);
        if(driver == null) {
            throw new ResourceNotFoundException("Your account might got removed from the System. Please reach out to traveldosthsupport@xyz.com");
        }
        Car car = carService.findCarById(carId);
        if(car == null) {
            throw new ResourceNotFoundException("Car with carId: "+carId+" not found in the system.");
        }
        driverService.requestCar(driver, car);
        return "Successfully requested for the car with carId: "+carId;
    }

    @Override
    @PostMapping("/withdraw-system")
    public String withdrawSystem() {
        Long driverId = 1L;  // TO DO. get driverId from JWT
        // NEED TO VALIDATION of checking if this driver is currently engaged in any bookings.
        log.info("driver with driverId: {} is leaving the system.",driverId);
        Driver driver = driverService.findDriverById(driverId);
        if(driver == null) {
            throw new ResourceNotFoundException("Your account might got removed from the System. Please reach out to traveldosthsupport@xyz.com");
        }
        if(driver.isAssignedWithCar()) {
            Car car = carService.findCarById(driver.getAssignedCarId());
            if(car != null) {
                carService.unAssignDriver(car);
            }
            driverService.unAssignCar(driver);
        }
        driverService.withdrawSystem(driver);
        return "Withdrawn from the system.";
    }

}
