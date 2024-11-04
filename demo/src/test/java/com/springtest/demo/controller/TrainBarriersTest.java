package com.springtest.demo.controller;

import com.springtest.demo.Repository.PassengerRepository;
import com.springtest.demo.service.EmailService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainBarriersTest {

    @Test
    //utilising the trace of methood calls with a mocked class
    //use verify to asesert uopn method callse made including the parameters passed in the method
    void passengerEntry() {
        final int PASSENGER_ID = 3;

        // Create mocks for dependencies
        PassengerRepository passengerRepositoryMock = mock(PassengerRepository.class);
        EmailService emailServiceMock = mock(EmailService.class);

        // Inject mocks into TrainBarriers
        TrainBarriers trainBarriers = new TrainBarriers(passengerRepositoryMock, emailServiceMock);

        // Call the method to test
        trainBarriers.passengerEntry(PASSENGER_ID);

        // Verify interactions with mocks
        verify(passengerRepositoryMock).registerPassengerOnTrain(PASSENGER_ID);
        verify(emailServiceMock).notifyPassenger(PASSENGER_ID);
    }
}
