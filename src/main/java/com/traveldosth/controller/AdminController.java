package com.traveldosth.controller;

import com.traveldosth.core.exception.InvalidOperationException;
import com.traveldosth.core.exception.ResourceNotFoundException;
import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.model.DriverCarRequest;
import com.traveldosth.service.CarService;
import com.traveldosth.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Api(value = "/admin", tags = "Admin Services", description = "Service for adding/removing a car, verifying/removing a driver, assigning car to a driver, getting details of cars, drivers, driver requests")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController implements AdminApi {

    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;

    @Override
    @PostMapping("/add-car")
    public String addCar(@RequestBody Car car) {
        log.info("Adding a new car.");
        if(car.getDriverId() != null) {
            Driver driver = driverService.findDriverById(car.getDriverId());
            if(driver == null) {
                throw new ResourceNotFoundException("No driver with driverId: "+car.getDriverId()+" found in the system");
            }
        }
        carService.addCar(car);
        return "Successfully added the car into the system.";
    }

    @Override
    @DeleteMapping("/remove-car")
    public String removeCar(@RequestBody Long carId) {
        log.info("Removing the car with carId: {}", carId);
        Car car = carService.findCarById(carId);
        if(car == null) {
            throw new ResourceNotFoundException("Car with carId: "+carId+" not found in the system.");
        }
        // NEED TO DO VALIDATION of checking if this car is used in any of the InCompleted bookings.
        if(car.getDriverId() != null) {
            Driver driver = driverService.findDriverById(car.getDriverId());
            driverService.unAssignCar(driver);
        }
        carService.removeCar(car);
        return "Successfully removed the car from the system.";
    }

    @Override
    @PostMapping("/verify-driver")
    public String verifyDriver(@RequestBody Long driverId) {
        log.info("Verifying the driver with driverId: {}", driverId);
        Driver driver = driverService.findDriverById(driverId);
        if(driver == null) {
            throw new ResourceNotFoundException("Driver with driverId: "+driverId+" not found in the system.");
        }
        driverService.verifyDriver(driver);
        return "Successfully verified the driver.";
    }

    @Override
    @DeleteMapping("/remove-driver")
    public String removeDriver(@RequestBody Long driverId) {
        log.info("Removing the driver with driverId: {}", driverId);
        Driver driver = driverService.findDriverById(driverId);
        if(driver == null) {
            throw new ResourceNotFoundException("Driver with driverId: "+driverId+" not found in the system.");
        }
        if(driver.isAssignedWithCar()) {
            Car car = carService.findCarById(driver.getAssignedCarId());
            if(car != null) {
                carService.unAssignDriver(car);
            }
            driverService.unAssignCar(driver);
        }
        driverService.removeDriver(driver);
        return "Successfully removed the driver from the system.";
    }

    @Override
    @PutMapping("/assign-car")
    public String assignCar(Long carId, Long driverId) {
        log.info("Assigning the Car with carId: {} to the Driver with driverId: {}", carId, driverId);
        Car car = carService.findCarById(carId);
        if(car == null) {
            throw new ResourceNotFoundException("Car with carId: "+carId+" not found in the system.");
        }
        if(car.getDriverId() != null) {
            throw new InvalidOperationException("Car with carId: "+carId+" is already assigned with driverId: "+driverId);
        }
        Driver driver = driverService.findDriverById(driverId);
        if(driver == null) {
            throw new ResourceNotFoundException("Driver with driverId: "+driverId+" not found in the system.");
        }
        if(!driver.isActive()) {
            throw new InvalidOperationException("Driver is Inactive.");
        }
        if(!driver.isVerified()) {
            throw new InvalidOperationException("Driver is not verified. Use add-driver endpoint to verify a driver.");
        }
        carService.assignCarToDriver(car, driver);
        return "Successfully assigned the car with carId: "+carId+" to the driver with driverId: "+driverId;
    }

    @Override
    @GetMapping("/get-cars")
    public List<Car> getCars() {
        log.info("Getting all the Cars present in the system.");
        return carService.getAllCars();
    }

    @Override
    @GetMapping("/get-drivers")
    public List<Driver> getDrivers() {
        log.info("Getting all the Drivers present in the system.");
        return driverService.getAllDrivers();
    }

    @Override
    @GetMapping("/get-drivers-car-requests")
    public List<DriverCarRequest> getDriverRequests() {
        log.info("Getting all the Car requests made by Drivers.");
        return driverService.getAllCarRequests();
    }
}
