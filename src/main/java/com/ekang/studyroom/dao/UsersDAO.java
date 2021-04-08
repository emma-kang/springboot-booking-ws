package com.ekang.studyroom.dao;

import com.ekang.studyroom.dto.UsersDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UsersDAO {
    protected static final String NAMESPACE = "com.ekang.studyroom.mapper.UsersMapper.";

    // @Autowired
    private final SqlSession sqlSession;

    public UsersDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<UsersDTO> getAllUsers() { return sqlSession.selectList(NAMESPACE + "getAllUsers"); }
    public UsersDTO getUserByID(int id) { return sqlSession.selectOne(NAMESPACE + "getUserById", id); }
    public int createUser(UsersDTO usersDTO) {
       return sqlSession.insert(NAMESPACE + "createUser", usersDTO);
    }
    public UsersDTO getUserByEmail(String email) {
        return sqlSession.selectOne(NAMESPACE + "getUserByEmail", email);
    }
    public int updatePassword(UsersDTO usersDTO) { return sqlSession.update(NAMESPACE + "updatePasswords", usersDTO); }
    public int deleteUserByID(int id) { return sqlSession.update(NAMESPACE + "deleteUserById", id); }

}
