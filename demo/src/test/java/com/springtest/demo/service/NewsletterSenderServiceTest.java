package com.springtest.demo.service;

import com.springtest.demo.Repository.SubscribersDatabase;
import com.springtest.demo.exception.ZeroSubscribersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NewsletterSenderServiceTest {

    @Test
    //checking constructor behavior, ensuring that dependencies are correctly initialized.
    public void constructorAssignsDatabase() {
        MessagingEngineService messagingEngine = mock(MessagingEngineService.class); // not used in test so can use mock version of the obj
        SubscribersDatabase subscribersDatabase = new SubscribersDatabase();
        NewsletterSenderService newsletterSender = new NewsletterSenderService(subscribersDatabase, messagingEngine);

        assertEquals(subscribersDatabase, newsletterSender.getSubscribersDatabase());
    }

    @Test
    // test verifies the behavior of the numberOfSubscribers method, which counts the subscribers in the SubscribersDatabase
    public void numberOfSubscribers() {
        SubscribersDatabase subscribersDatabaseMock = mock(SubscribersDatabase.class);
        MessagingEngineService messagingEngineMock = mock(MessagingEngineService.class);

        NewsletterSenderService sender = new NewsletterSenderService(subscribersDatabaseMock, messagingEngineMock);

        List<String> subscribersList = Arrays.asList("email1", "email2", "email3");
        when(subscribersDatabaseMock.getSubscribers()).thenReturn(subscribersList);

        assertEquals(3, sender.numberOfSubscribers());
    }

    @Test
    /*This test uses a spy to mock only the specific behavior of numberOfSubscribers()
    while letting other methods in NewsletterSenderService (like sendNewsletter()) execute as they would in the real class.
     The test checks if the code throws the exception correctly in the case of zero subscribers.*/
    public void zeroSubscribersThrown() {
        SubscribersDatabase subscribersDatabaseMock = mock(SubscribersDatabase.class);
        MessagingEngineService messagingEngineMock = mock(MessagingEngineService.class);

        NewsletterSenderService newsletterSender = new NewsletterSenderService(subscribersDatabaseMock, messagingEngineMock);
        NewsletterSenderService newsletterSenderSpy = spy(newsletterSender);

        when(newsletterSenderSpy.numberOfSubscribers()).thenReturn(0);

        assertThrows(ZeroSubscribersException.class, () -> newsletterSenderSpy.sendNewsletter("SUBJECT"));

        // Reset the spy to call the real numberOfSubscribers() method, not the mocked return of 0
        doCallRealMethod().when(newsletterSenderSpy).numberOfSubscribers();

        // Assuming there’s a real return value from the database mock, verify with assertEquals
        List<String> subscribersList = Arrays.asList("email1", "email2", "email3");
        when(subscribersDatabaseMock.getSubscribers()).thenReturn(subscribersList);

        // Check that the real method returns the actual count
        assertEquals(3, newsletterSenderSpy.numberOfSubscribers());

    }
}

/*
*
* Key Differences Between a Mock and a Spy
Mock: Completely replaces an object's behavior. When you mock an object, none of its original methods are called unless explicitly specified.
Spy: Allows you to use real method calls, unless a method is explicitly stubbed. Spies are “partial mocks” in that they let you mock certain behaviors while still calling the real methods for others.
* */