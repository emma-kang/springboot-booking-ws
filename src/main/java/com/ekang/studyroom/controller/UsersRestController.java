package com.ekang.studyroom.controller;

import com.ekang.studyroom.dao.UsersDAO;
import com.ekang.studyroom.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersRestController {
    final static Logger logger = LoggerFactory.getLogger(UsersRestController.class);

    @Autowired
    private UsersDAO usersDAO;

    @GetMapping(path="/users")
    public @ResponseBody List<UsersDTO> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @GetMapping(path="/users/user")
    public @ResponseBody UsersDTO getUserById(@RequestParam("id") int id) {
        logger.info("getUserById(), userId=" + id);
        return usersDAO.getUserByID(id);
    }

//    @PostMapping(path="/users/login")
//    public
}
