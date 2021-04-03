package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SeatsDTO {
    private int seatId;
    private int roomId;
    private Date openDate;
    private Date closeDate;

    public SeatsDTO(int seatId, int roomId, Date openDate, Date closeDate) {
        this.seatId = seatId;
        this.roomId = roomId;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

}
