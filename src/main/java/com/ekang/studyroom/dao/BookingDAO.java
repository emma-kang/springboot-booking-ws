package com.ekang.studyroom.dao;

import com.ekang.studyroom.dto.BookingDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookingDAO {
    protected static final String NAMESPACE = "com.ekang.studyroom.mapper.BookingMapper.";
    private final SqlSession sqlSession;

    public BookingDAO(SqlSession sqlSession) { this.sqlSession = sqlSession; }

    public BookingDTO getBookingInfoByUserId(int userId) {
        return sqlSession.selectOne(NAMESPACE + "getBookingInfoByUserId", userId);
    }

    public BookingDTO getBookingInfoByBookingId(int bookId) {
        return sqlSession.selectOne(NAMESPACE + "getBookingInfoByBookingId", bookId);
    }

    public List<BookingDTO> getBookingInfoByRoom(int roomId) {
        return sqlSession.selectList(NAMESPACE + "getBookingInfoByRoom", roomId);
    }

    public int addNewBooking(BookingDTO newBooking) {
        return sqlSession.insert(NAMESPACE + "addNewBooking", newBooking);
    }

    public int updateBooking(BookingDTO booking) {
        return sqlSession.update(NAMESPACE + "updateBooking", booking);
    }

    public int cancelBooking(int bookingId) {
        return sqlSession.update(NAMESPACE + "cancelBooking", bookingId);
    }

}
