package com.ekang.studyroom.controller;

import com.ekang.studyroom.dto.BookingDTO;
import com.ekang.studyroom.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookingRestController {
    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path="/booking/user")
    public @ResponseBody BookingDTO getBookingInfoByUserId(@RequestParam("userId") int userId) {
        return bookingService.getBookingInfoByUserId(userId);
    }

    @GetMapping(path="/booking/room")
    public @ResponseBody List<BookingDTO> getBookingInfoByRoom(@RequestParam("roomId") int roomId) {
        return bookingService.getBookingInfoByRoom(roomId);
    }

    @PostMapping(path="/booking/new")
    public Map<String, String> addNewBooking(@RequestBody BookingDTO newBooking) {
        return bookingService.addNewBooking(newBooking);
    }

    @PatchMapping(path="/booking/update")
    public Map<String, String> updateBooking(@RequestParam("bookId") int bookingId, @RequestBody Map<String, String> info) {
        return bookingService.updateBooking(bookingId, info);
    }

    @PatchMapping(path="/booking/cancel")
    public Map<String, String> cancelBooking(@RequestParam("bookId") int bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

}
