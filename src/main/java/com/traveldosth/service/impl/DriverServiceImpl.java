package com.traveldosth.service.impl;

import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.model.DriverCarRequest;
import com.traveldosth.repository.DriverCarRequestRepository;
import com.traveldosth.repository.DriverRepository;
import com.traveldosth.service.CarService;
import com.traveldosth.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverCarRequestRepository driverCarRequestRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public List<DriverCarRequest> getAllCarRequests() {
        return driverCarRequestRepository.findAll();
    }

    @Override
    public Driver findDriverById(Long driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        return driver.isPresent() ? driver.get() : null;
    }

    @Override
    public void verifyDriver(Driver driver) {
        driver.setVerified(true);
        driverRepository.save(driver);
    }

    @Override
    public void removeDriver(Driver driver) {
        // NEED TO REMOVE THEIR ACCOUNT FROM SYSTEM i.e, to remove from UserDetail entity.
        driverRepository.delete(driver);
    }

    @Override
    public void requestCar(Driver driver, Car car) {
        // NEED TO DO VALIDATE for duplicate requests.
        DriverCarRequest driverCarRequest = new DriverCarRequest(driver.getDriverId(), car.getDriverId());
        driverCarRequestRepository.save(driverCarRequest);
    }

    @Override
    public void withdrawSystem(Driver driver) {
        driver.setActive(false);
        if(driver.getAssignedCarId() != null) {

        }
        driverRepository.save(driver);
    }

    @Override
    public void unAssignCar(Driver driver) {
        driver.setAssignedCarId(null);
        driver.setAssignedWithCar(false);
        driverRepository.save(driver);
    }

}
