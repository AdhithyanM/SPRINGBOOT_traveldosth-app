package com.traveldosth.repository;

import com.traveldosth.model.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
