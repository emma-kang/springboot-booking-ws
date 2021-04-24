package com.ekang.studyroom.controller;

import com.ekang.studyroom.dao.BookingDAO;
import com.ekang.studyroom.dto.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingRestController {
    final static Logger logger = LoggerFactory.getLogger(BookingRestController.class);

    private final BookingDAO bookingDAO;

    public BookingRestController(BookingDAO bookingDAO) { this.bookingDAO = bookingDAO; }

    @GetMapping(path="/booking/user")
    public @ResponseBody BookingDTO getBookingInfoByUserId(@RequestParam("userId") int userId) {
        return bookingDAO.getBookingInfoByUserId(userId);
    }

    @GetMapping(path="/booking/room")
    public @ResponseBody List<BookingDTO> getBookingInfoByRoom(@RequestParam("roomId") int roomId) {
        return bookingDAO.getBookingInfoByRoom(roomId);
    }

    @PostMapping(path="/booking/new")
    public Map<String, String> addNewBooking(@RequestBody BookingDTO newBooking) {
        Map<String, String> response = new HashMap<>();
        logger.info("Creating new booking schedule for userID:" + newBooking.getUserId());

        try {
            int resultCode = bookingDAO.addNewBooking(newBooking);

            if (resultCode == 1) {
                response.put("status", "201");
            } else {
                response.put("status", "500");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating new booking" + newBooking.toString());
        }

        return response;
    }

    /**
     * Assume only schedule time can be updated 
     * @param bookingId
     * @param info
     * @return
     */
    @PatchMapping(path="/booking/update")
    public Map<String, String> updateBooking(@RequestParam("bookId") int bookingId, @RequestBody Map<String, String> info) {
        Map<String, String> response = new HashMap<>();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Updating booking schedule for bookId:" + bookingId);

        try {
            if (info.isEmpty())
                return null;

            BookingDTO booking = bookingDAO.getBookingInfoByBookingId(bookingId);
            // convert string to date
            Date startDateTime = sdFormat.parse(info.get("startTime"));
            Date endDateTime = sdFormat.parse(info.get("endTime"));
            // set updated date times
            booking.setStartTime(startDateTime);
            booking.setEndTime(endDateTime);

            int resultCode = bookingDAO.updateBooking(booking);

            if (resultCode == 1) {
                response.put("status", "200");
            } else {
                response.put("status", "500");
                response.put("message", "Error occurred while processing query");
            }

        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("Error occurring while canceling booking" + e);
        }

        return response;
    }

    @PatchMapping(path="/booking/cancel")
    public Map<String, String> cancelBooking(@RequestParam("bookId") int bookingId) {
        Map<String, String> response = new HashMap<>();
        logger.info("Canceling booking schedule for bookId:" + bookingId);

        try {
            int resultCode = bookingDAO.cancelBooking(bookingId);

            if (resultCode == 1) {
                response.put("status", "200");
            } else {
                response.put("status", "500");
                response.put("message", "Error occurred while processing query");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurring while canceling booking" + e);
        }

        return response;
    }

}
