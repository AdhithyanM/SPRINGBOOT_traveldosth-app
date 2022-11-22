package com.traveldosth.repository;

import com.traveldosth.model.DriverCarRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverCarRequestRepository extends JpaRepository<DriverCarRequest, Long> {

}
