package com.ekang.studyroom.service;

import com.ekang.studyroom.dto.UsersDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<UsersDTO> getAllUsers();
    public UsersDTO getUserById(int id);
    public Map<String, String> createUser(UsersDTO usersDTO);
    public Map<String, String> signInUser(Map<String, String> info);
    public Map<String, String> updatePasswords(Map<String, String> info);
    public Map<String, String> deleteUsersByID(int id);
}
