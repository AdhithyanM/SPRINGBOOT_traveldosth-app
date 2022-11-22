package com.traveldosth.model;

import com.traveldosth.model.enums.BookingStateEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long bookingId;

    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(example = "124", required = true)
    private Long userId;

    @Column(name = "car_id", nullable = false)
    @ApiModelProperty(example = "145", required = true)
    private Long carId;

    @Column(name = "driver_id", nullable = false)
    @ApiModelProperty(example = "212", required = true)
    private Long driverId;

    @Column(name = "start_location_id", nullable = false)
    @ApiModelProperty(example = "194", required = true)
    private Long startLocationId;

    @Column(name = "end_location_id", nullable = false)
    @ApiModelProperty(example = "2123", required = true)
    private Long endLocationId;

    @Column(name = "booking_status", nullable = false)
    @ApiModelProperty(example = "COMPLETED", required = true)
    private BookingStateEnum bookingStatus;

    public Booking(Long userId, Long carId, Long driverId, Long startLocationId, Long endLocationId) {
        this.userId = userId;
        this.carId = carId;
        this.driverId = driverId;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
        this.bookingStatus = BookingStateEnum.OPEN;
    }

}
