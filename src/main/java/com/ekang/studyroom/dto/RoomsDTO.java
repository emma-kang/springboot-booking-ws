package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RoomsDTO {
    private int roomId;
    private String roomName;
    private Date openDate;
    private Date closeDate;

    public RoomsDTO(int roomId, String roomName, Date openDate, Date closeDate) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
