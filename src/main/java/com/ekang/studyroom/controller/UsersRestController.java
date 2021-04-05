package com.ekang.studyroom.controller;

import com.ekang.studyroom.dao.UsersDAO;
import com.ekang.studyroom.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsersRestController {
    final static Logger logger = LoggerFactory.getLogger(UsersRestController.class);

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path="/users")
    public @ResponseBody List<UsersDTO> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @GetMapping(path="/users/user")
    public @ResponseBody UsersDTO getUserById(@RequestParam("id") int id) {
        logger.info("getUserById(), userId=" + id);
        return usersDAO.getUserByID(id);
    }

    /**
     * CreateUser
     * @param usersDTO
     * {
     *     "firstName": "Jane",
     *     "lastName": "Doe",
     *     "isActive": true,
     *     "email": "test@email.com",
     *     "passwords": "Test1234"
     * }
     * @return
     * {
     *     "message": "success",
     *     "userId": "9"
     * }
     */
    @PostMapping(path="/users/signup")
    public Map<String, String> createUser(@RequestBody UsersDTO usersDTO) {
        Map<String, String> result = new HashMap<>();

        logger.info("Started to create new user");

        try {
            // BCrypt User password
            usersDTO.setPasswords(passwordEncoder.encode(usersDTO.getPasswords()));
            int resultCode = usersDAO.createUser(usersDTO);

            if (resultCode == 0) {
                result.put("message", "error");
            } else {
                result.put("userId", String.valueOf(usersDTO.getUserId()));
                result.put("message", "success");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating user" + usersDTO.toString());
        }

        return result;

    }

//    @PostMapping(path="/users/signin")
//    public Map<String, String> signInUser(@RequestBody Map<String, String> info) {
//
//    }
}
