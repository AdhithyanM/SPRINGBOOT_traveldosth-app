package com.traveldosth.model.dto.request;

import com.traveldosth.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModelProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @ApiModelProperty(example = "1", required = true)
    private Long carId;

    @ApiModelProperty(required = true)
    private Location startLocation;

    @ApiModelProperty(required = true)
    private Location endLocation;

}
