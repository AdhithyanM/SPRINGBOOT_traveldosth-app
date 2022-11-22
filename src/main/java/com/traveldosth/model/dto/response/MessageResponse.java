package com.traveldosth.model.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class MessageResponse {

    @ApiModelProperty(example = "Response message string", required = true)
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
