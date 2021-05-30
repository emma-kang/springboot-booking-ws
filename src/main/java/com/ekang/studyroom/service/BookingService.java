package com.ekang.studyroom.service;

import com.ekang.studyroom.dto.BookingDTO;

import java.util.List;
import java.util.Map;

public interface BookingService {
    public BookingDTO getBookingInfoByUserId(int userid);
    public List<BookingDTO> getBookingInfoByRoom(int roomid);
    public Map<String, String> addNewBooking(BookingDTO bookingDTO);
    public Map<String, String> updateBooking(int bookingId, Map<String, String> info);
    public Map<String, String> cancelBooking(int bookingId);
}
