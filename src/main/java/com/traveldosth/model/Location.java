package com.traveldosth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Location")
public class Location {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long locationId;

    @Column(name = "latitude", nullable = false)
    @ApiModelProperty(example = "36.385913", required = true)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @ApiModelProperty(example = "-127.441406", required = true)
    private Double longitude;

}
