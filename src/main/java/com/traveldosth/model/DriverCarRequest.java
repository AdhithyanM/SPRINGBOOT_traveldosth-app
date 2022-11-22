package com.traveldosth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel
@NoArgsConstructor
@Data
@Entity
@Table(name = "DriverCarRequest")
public class DriverCarRequest {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long requestId;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "car_id", nullable = false)
    @ApiModelProperty(example = "123", required = true)
    private Long carId;

    public DriverCarRequest(Long driverId, Long carId) {
        this.driverId = driverId;
        this.carId = carId;
    }
}
