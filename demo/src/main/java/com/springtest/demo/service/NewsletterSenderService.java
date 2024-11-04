package com.springtest.demo.service;

import com.springtest.demo.Repository.SubscribersDatabase;
import com.springtest.demo.exception.ZeroSubscribersException;

import java.util.List;

public class NewsletterSenderService {
    private SubscribersDatabase subscribersDatabase;
    private MessagingEngineService messagingEngine;

    public NewsletterSenderService(SubscribersDatabase subscribersDatabase, MessagingEngineService messagingEngine) {
        this.subscribersDatabase = subscribersDatabase;
        this.messagingEngine = messagingEngine;
    }

    public void sendNewsletter(String subject){
        List<String> emails = subscribersDatabase.getSubscribers();

        if(numberOfSubscribers() == 0){
            throw new ZeroSubscribersException();
        }
        messagingEngine.sendEmail(subject, emails);
    }

    public int numberOfSubscribers(){
        return subscribersDatabase.getSubscribers().size();
    }

    public SubscribersDatabase getSubscribersDatabase() {
        return subscribersDatabase;
    }

    public MessagingEngineService getMessagingEngine() {
        return messagingEngine;
    }
}
