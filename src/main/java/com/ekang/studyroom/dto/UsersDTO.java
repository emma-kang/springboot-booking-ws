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

    public UsersDTO(int id, Date createDate, String firstName, String lastName) {
        this.userId = id;
        this.createDate = createDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
