package com.traveldosth.model;

import com.traveldosth.model.enums.CarSeatCapacityEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel
@NoArgsConstructor
@Data
@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long carId;

    @Column(name = "car_model", nullable = false)
    @ApiModelProperty(example = "Tata Nexon EV", required = true)
    private String carModel;

    @Column(name = "seat_capacity", nullable = false)
    @ApiModelProperty(example = "THREE", required = true)
    private CarSeatCapacityEnum seatCapacity;

    @Column(name = "is_available", columnDefinition = "boolean default true", nullable = false)
    @ApiModelProperty(example = "true", required = true)
    private boolean isAvailable;

    @Column(name = "driver_id", nullable = true)
    @ApiModelProperty(example = "243")
    private Long driverId;

}
