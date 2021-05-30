package com.ekang.studyroom.service;

import com.ekang.studyroom.UsersDAO;
import com.ekang.studyroom.config.security.JwtTokenProvider;
import com.ekang.studyroom.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UsersDAO usersDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Override
    public UsersDTO getUserById(int id) {
        logger.info("getUserById(), userId=" + id);
        return usersDAO.getUserByID(id);
    }

    @Override
    public Map<String, String> createUser(UsersDTO usersDTO) {
        Map<String, String> response = new HashMap<>();

        logger.info("Started to create new user");

        try {
            // BCrypt User password
            usersDTO.setPasswords(passwordEncoder.encode(usersDTO.getPasswords()));
            int resultCode = usersDAO.createUser(usersDTO);

            if (resultCode == 1) {
                response.put("status", "201");
                response.put("userId", String.valueOf(usersDTO.getUserId()));
            } else {
                response.put("status", "500");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating user" + usersDTO.toString());
        }

        return response;
    }

    @Override
    public Map<String, String> signInUser(Map<String, String> info) {
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

    @Override
    public Map<String, String> updatePasswords(Map<String, String> info) {
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

    @Override
    public Map<String, String> deleteUsersByID(int id) {
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
