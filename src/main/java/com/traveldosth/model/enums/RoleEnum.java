package com.traveldosth.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN(0), ROLE_DRIVER(2), ROLE_CUSTOMER(1);

    private int role;

}