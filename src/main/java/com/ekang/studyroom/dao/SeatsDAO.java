package com.ekang.studyroom.dao;

import com.ekang.studyroom.dto.SeatsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatsDAO {
    protected static final String NAMESPACE = "com.ekang.studyroom.mapper.SeatsMapper.";

    @Autowired
    private SqlSession sqlSession;

    public SeatsDAO() {}

    public SeatsDTO getSeatsByID(int id) {
        return sqlSession.selectOne(NAMESPACE + "getSeatsByID", id);
    }

    public List<SeatsDTO> getAllSeats() {
        return sqlSession.selectList(NAMESPACE + "getAllseats");
    }

}
