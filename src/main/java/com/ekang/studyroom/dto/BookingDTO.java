package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class BookingDTO {
    private int bookingId;
    private int userId;
    private int roomId;
    private int seatId;
    private Date startTime;
    private Date endTime;
    private String status;

    public BookingDTO(int userId, int roomId, int seatId, Date startTime, Date endTime) {
        this.userId = userId;
        this.roomId = roomId;
        this.seatId = seatId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
