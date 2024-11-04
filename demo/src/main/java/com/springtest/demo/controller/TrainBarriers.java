package com.springtest.demo.controller;

import com.springtest.demo.Repository.PassengerRepository;
import com.springtest.demo.service.EmailService;

public class TrainBarriers {
    private PassengerRepository repository;
    private EmailService emailService;

    public TrainBarriers(PassengerRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void passengerEntry(int passengerId){

        repository.registerPassengerOnTrain(passengerId);
        emailService.notifyPassenger(passengerId);
    }
}
