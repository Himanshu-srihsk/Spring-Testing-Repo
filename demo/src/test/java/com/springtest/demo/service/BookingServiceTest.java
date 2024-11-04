package com.springtest.demo.service;

import com.springtest.demo.dao.HotelDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  //@ExtendWith(MockitoExtension.class) isnot necessary with manual mock setup
class BookingServiceTest {

    @Mock
    private HotelDao hotelDaoMock;

    @InjectMocks
    private BookingService bookingManager;


    @BeforeEach
    public void setup() throws SQLException {
        List<String> availableRooms = Arrays.asList("A");
        when(hotelDaoMock.fetchAvailableRooms()).thenReturn(availableRooms);
    }

//    private HotelDao hotelDaoMock;
//    private BookingService bookingManager;
//
//    @BeforeEach
//    public void setup() throws SQLException {
//        // Manually create mock and service instance
//        hotelDaoMock = mock(HotelDao.class);
//        bookingManager = new BookingService(hotelDaoMock);
//
//        // Set up the mock behavior
//        List<String> availableRooms = Arrays.asList("A");
//        when(hotelDaoMock.fetchAvailableRooms()).thenReturn(availableRooms);
//    }


    @Test
    public void checkAvailableRoomsTrue() throws SQLException {
        assertTrue(bookingManager.checkRoomAvailability("A"));
    }

    @Test
    public void checkAvailableRoomsFalse() throws SQLException {
        assertFalse(bookingManager.checkRoomAvailability("B"));
    }
}
