package com.ekang.studyroom.controller;

import com.ekang.studyroom.SeatsDAO;
import com.ekang.studyroom.dto.SeatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatsRestController {
    final static Logger logger = LoggerFactory.getLogger(SeatsRestController.class);

    private final SeatsDAO seatsDAO;

    public SeatsRestController(SeatsDAO seatsDAO) {
        this.seatsDAO = seatsDAO;
    }

    @GetMapping(path="/seats")
    public @ResponseBody List<SeatsDTO> getAllSeats() {
        return seatsDAO.getAllSeats();
    }

    @GetMapping(path="/seats/seat")
    public @ResponseBody SeatsDTO getSeatsByID(@RequestParam("id") int id) {
        return seatsDAO.getSeatsByID(id);
    }

    @GetMapping(path="/seats/room")
    public @ResponseBody List<SeatsDTO> getSeatsByRoomID(@RequestParam("roomId") int roomId) {
        return seatsDAO.getSeatsByRoomID(roomId);
    }
}
