package com.traveldosth.model.dto.request;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @ApiModelProperty(example = "SampleUser", required = true)
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @ApiModelProperty(example = "samplemail@gmail.com", required = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @ApiModelProperty(example = "0 for ADMIN, 1 for DRIVER, 2 for CUSTOMER", required = true)
    private int roleId;

    @ApiModelProperty(example = "Sample!342@", required = true)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}

