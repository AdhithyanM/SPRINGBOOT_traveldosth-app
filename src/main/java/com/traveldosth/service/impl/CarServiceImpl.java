package com.traveldosth.service.impl;

import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;
import com.traveldosth.repository.CarRepository;
import com.traveldosth.repository.DriverRepository;
import com.traveldosth.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.fetchAvailableCars();
    }

    @Override
    public List<Car> getUnassignedCars() {
        return carRepository.fetchUnAssignedCars();
    }

    @Override
    public Car findCarById(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        return car.isPresent() ? car.get() : null;
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void removeCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void assignCarToDriver(Car car, Driver driver) {
        // NEED TO DO VALIDATION for car and driver
        car.setDriverId(driver.getDriverId());
        driver.setAssignedWithCar(true);
        driver.setAssignedCarId(car.getCarId());
        carRepository.save(car);
        driverRepository.save(driver);
    }

    @Override
    public void unAssignDriver(Car car) {
        car.setDriverId(null);
        carRepository.save(car);
    }
}
