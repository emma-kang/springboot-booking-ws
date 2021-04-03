package com.ekang.studyroom.dao;

import com.ekang.studyroom.dto.UsersDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAO {
    protected static final String NAMESPACE = "com.ekang.studyroom.mapper.UsersMapper.";

    @Autowired
    private SqlSession sqlSession;

    public List<UsersDTO> getAllUsers() { return sqlSession.selectList(NAMESPACE + "getAllUsers"); }
    public UsersDTO getUserByID(int id) { return sqlSession.selectOne(NAMESPACE + "getUserById", id); }

}
