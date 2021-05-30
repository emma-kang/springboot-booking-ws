package com.ekang.studyroom.controller;

import com.ekang.studyroom.UsersDAO;
import com.ekang.studyroom.dto.UsersDTO;
import com.ekang.studyroom.config.security.JwtTokenProvider;
import com.ekang.studyroom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsersRestController {
    private final UserService userService;

    public UsersRestController(UsersDAO usersDAO, UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public @ResponseBody List<UsersDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/users/user")
    public @ResponseBody UsersDTO getUserById(@RequestParam("id") int id) {
        return userService.getUserById(id);
    }

    /**
     * CreateUser
     * @param
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
        return userService.createUser(newUser);
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
        return userService.signInUser(info);
    }

    @PatchMapping(path="/users/update/password")
    public Map<String, String> updatePasswords(@RequestBody Map<String, String> info) {
        return userService.updatePasswords(info);
    }

    // This will set the user as inactive instead of deleting existed data
    @PatchMapping(path="/users/delete")
    public Map<String, String> deleteUserByID(@RequestParam int id) {
        return userService.deleteUsersByID(id);
    }
}
