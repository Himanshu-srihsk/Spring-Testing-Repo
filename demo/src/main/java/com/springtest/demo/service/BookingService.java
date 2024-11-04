package com.springtest.demo.service;

import com.springtest.demo.dao.HotelDao;

import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private HotelDao dao;

    public BookingService(HotelDao dao) {
        this.dao = dao;
    }

    public boolean checkRoomAvailability(String roomName) throws SQLException {

        List<String> roomsAvailable = dao.fetchAvailableRooms();
        return roomsAvailable.contains(roomName);
    }

}
