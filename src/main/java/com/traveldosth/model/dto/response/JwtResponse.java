package com.traveldosth.model.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class JwtResponse {

    @ApiModelProperty(example = "242nl421n2312313-af3gefr32")
    private String token;

    @ApiModelProperty(example = "Bearer")
    private String type = "Bearer";

    @ApiModelProperty(example = "132")
    private Long userId;

    @ApiModelProperty(example = "SampleUser")
    private String username;

    @ApiModelProperty(example = "samplemail@gmail.com")
    private String email;

    @ApiModelProperty(example = "['DRIVER']")
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.userId = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
