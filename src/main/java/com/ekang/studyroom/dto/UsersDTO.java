package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String email;
    private String passwords;

    public UsersDTO(String firstName, String lastName, boolean isActive, String email, String passwords) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.email = email;
        this.passwords = passwords;
    }
}
