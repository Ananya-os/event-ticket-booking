package com.ananya.event_ticket_booking.dto;

import com.ananya.event_ticket_booking.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role;

}