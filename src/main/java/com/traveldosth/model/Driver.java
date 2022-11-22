package com.traveldosth.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Driver")
public class Driver {

    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private boolean isVerified;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(name = "is_assigned_with_car", columnDefinition = "boolean default false")
    private boolean isAssignedWithCar;

    @Column(name = "assigned_car_id", nullable = true)
    private Long assignedCarId;

    // other stuff like driver license and fine-grained data regarding driver.

    private Driver() {}
    public Driver(Long driverId) {
        this.driverId = driverId;
    }
}
