package com.traveldosth.service;

import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.model.DriverCarRequest;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    List<DriverCarRequest> getAllCarRequests();

    Driver findDriverById(Long driverId);

    void verifyDriver(Driver driver);

    void removeDriver(Driver driver);

    void requestCar(Driver driver, Car car);

    void withdrawSystem(Driver driver);

    void unAssignCar(Driver driver);

}
