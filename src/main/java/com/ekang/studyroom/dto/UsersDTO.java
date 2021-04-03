package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UsersDTO {
    private int userId;
    private Date createDate;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String email;
    private String passwords;

    public UsersDTO(Date createDate, String firstName, String lastName, String email, String passwords) {
        this.createDate = createDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwords = passwords;
    }
}
