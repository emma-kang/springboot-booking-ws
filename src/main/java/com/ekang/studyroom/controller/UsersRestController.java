package com.ekang.studyroom.controller;

import com.ekang.studyroom.dao.UsersDAO;
import com.ekang.studyroom.dto.UsersDTO;
import com.ekang.studyroom.config.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsersRestController {
    final static Logger logger = LoggerFactory.getLogger(UsersRestController.class);

    private final UsersDAO usersDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public UsersRestController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

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
     *     "status": "201",
     *     "userId": "9"
     * }
     */
    @PostMapping(path="/users/signup")
    public Map<String, String> createUser(@RequestBody UsersDTO newUser) {
        Map<String, String> response = new HashMap<>();

        logger.info("Started to create new user");

        try {
            // BCrypt User password
            newUser.setPasswords(passwordEncoder.encode(newUser.getPasswords()));
            int resultCode = usersDAO.createUser(newUser);

            if (resultCode == 1) {
                response.put("status", "201");
                response.put("userId", String.valueOf(newUser.getUserId()));
            } else {
                response.put("status", "500");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating user" + newUser.toString());
        }

        return response;

    }

    /**
     * Update User Password
     * @param info
     * {
     *     "email":"test@email.com",
     *     "passwords": "Test1234"
     * }
     * @return
     */
    @PostMapping(path="/users/signin")
    public Map<String, String> signInUser(@RequestBody Map<String, String> info) {
        Map<String, String> response = new HashMap<>();

        UsersDTO user = usersDAO.getUserByEmail(info.get("email"));

        if (user == null) {
            response.put("status", "404");
            response.put("message", "Not found account");
        } else if (!passwordEncoder.matches(info.get("passwords"),user.getPasswords())) {
            response.put("status", "400");
            response.put("message", "Not Match Password");
        } else {
            response.put("status", "200");
            response.put("token", jwtTokenProvider.issue(user.getUserId()).getToken());
        }

        return response;
    }

    @PatchMapping(path="/users/update/password")
    public Map<String, String> updatePasswords(@RequestBody Map<String, String> info) {
        Map<String, String> response = new HashMap<>();

        try {
            if (!info.isEmpty()) {
                // find current user information
                UsersDTO user = usersDAO.getUserByID(Integer.parseInt(info.get("userId")));
                // encode new passwords string
                user.setPasswords(passwordEncoder.encode(info.get("newPassword")));
                int resultCode = usersDAO.updatePassword(user);

                if (resultCode == 1) {
                    response.put("status", "200");
                } else {
                    response.put("status", "500");
                    response.put("message", "Error occurred while processing query");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurring while updating password:" + e);
        }

        return response;
    }

    // This will set the user as inactive instead of deleting existed data
    @PatchMapping(path="/users/delete")
    public Map<String, String> deleteUserByID(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();

        try {
            if (id > 0) {
                int resultCode = usersDAO.deleteUserByID(id);

                if (resultCode == 1) {
                    response.put("status", "200");
                } else {
                    response.put("status", "500");
                    response.put("message", "Error occurred while processing query");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurring while deleting user:" + e);
        }

        return response;
    }
}
