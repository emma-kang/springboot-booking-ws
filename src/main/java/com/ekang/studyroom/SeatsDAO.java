package com.ekang.studyroom;

import com.ekang.studyroom.dto.SeatsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatsDAO {
    protected static final String NAMESPACE = "com.ekang.studyroom.mapper.SeatsMapper.";
    private SqlSession sqlSession;

    public SeatsDAO(SqlSession sqlSession) { this.sqlSession = sqlSession; }

    public List<SeatsDTO> getAllSeats() {
        return sqlSession.selectList(NAMESPACE + "getAllSeats");
    }
    public SeatsDTO getSeatsByID(int id) {
        return sqlSession.selectOne(NAMESPACE + "getSeatsByID", id);
    }
    public List<SeatsDTO> getSeatsByRoomID(int roomId) {
        return sqlSession.selectList(NAMESPACE + "getSeatsByRoomID", roomId);
    }


}
