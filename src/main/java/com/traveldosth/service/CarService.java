package com.traveldosth.service;

import com.traveldosth.model.Car;
import com.traveldosth.model.Driver;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();         // To check by the admin

    List<Car> getAvailableCars();   // To book by the customer

    List<Car> getUnassignedCars();  // To assign them to drivers

    Car findCarById(Long carId);

    void addCar(Car car);

    void updateCar(Car car);

    void removeCar(Car car);

    void assignCarToDriver(Car car, Driver driver);

    void unAssignDriver(Car car);

}
