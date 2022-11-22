package com.traveldosth.controller;

import com.traveldosth.core.security.jwt.JwtUtils;
import com.traveldosth.core.security.services.UserDetailsImpl;
import com.traveldosth.model.Driver;
import com.traveldosth.model.User;
import com.traveldosth.model.dto.request.LoginRequest;
import com.traveldosth.model.dto.request.SignupRequest;
import com.traveldosth.model.dto.response.JwtResponse;
import com.traveldosth.model.dto.response.MessageResponse;
import com.traveldosth.model.enums.RoleEnum;
import com.traveldosth.repository.DriverRepository;
import com.traveldosth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Api(value = "/api/auth", tags = "Authorization Service", description = "Get the auth token by signing in or register yourself to sign in")
@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        int roleId = signUpRequest.getRoleId();
        RoleEnum role;
        AtomicBoolean isDriver = new AtomicBoolean(false);

        switch (roleId) {
            case 0:         // ADMIN
                role = RoleEnum.ROLE_ADMIN;

                break;
            case 1:         // DRIVER
                role = RoleEnum.ROLE_DRIVER;
		    isDriver.set(true);

                break;
            default:        // any other roleId is considered as CUSTOMER
                role = RoleEnum.ROLE_CUSTOMER;
        }

        user.setRole(role);
        user = userRepository.save(user);
        if(isDriver.get()) {
            Driver driver = new Driver(user.getId());
            driverRepository.save(driver);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}