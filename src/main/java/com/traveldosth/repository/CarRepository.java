package com.traveldosth.repository;

import com.traveldosth.model.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from Car c where c.is_available=1 and c.driver_id is not null", nativeQuery = true)
    List<Car> fetchAvailableCars();

    @Query(value = "select * from Car c where c.driver_id is null", nativeQuery = true)
    List<Car> fetchUnAssignedCars();

}