package com.traveldosth.model.dto.request;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(example = "SampleUser", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(example = "Sample!342@", required = true)
    private String password;

}