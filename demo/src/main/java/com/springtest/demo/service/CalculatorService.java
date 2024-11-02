package com.springtest.demo.service;

import com.springtest.demo.exception.InvalidInputException;
import org.springframework.stereotype.Service;



public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public String getMessage() {
        return null; // Assuming the initial state is null for the message
    }

    public double divide(double numerator, double denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        return numerator / denominator;
    }

    public void checkForInput(String input) throws InvalidInputException {
        // Example logic for checking the input
        if (!input.matches("\\d+")) { // Only allows digits
            throw new InvalidInputException("Input must be a valid number.");
        }
    }

}
