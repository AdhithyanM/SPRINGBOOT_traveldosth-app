package com.traveldosth.controller;

import com.traveldosth.model.dto.request.LoginRequest;
import com.traveldosth.model.dto.request.SignupRequest;
import com.traveldosth.model.dto.response.JwtResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AuthApi {

    @ApiOperation(value = "Sign in to the System to get JWT for accessing APIs", response = JwtResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully signed in"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);

    @ApiOperation(value = "Register yourself in to the system for experiencing the services provided", response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully registered"),
                    @ApiResponse(code = 401, message = "Not Authorized!")
            }
    )
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest);

}
