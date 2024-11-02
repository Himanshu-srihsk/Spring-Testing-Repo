package com.springtest.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StundentScoreCalculatorTest {
    private StundentScoreCalculator stundentScoreCalculator;

    @BeforeEach
    public void init(){
        stundentScoreCalculator = new StundentScoreCalculator();
    }

    public static Object[][] testValues(){
        return new Object[][]{
                new Object[]{50, 50,  2500},
                new Object[]{-10, 50, -1},
                new Object[]{50, -1, -1},
                new Object[]{-1, -1, -1},
                new Object[]{150, 50, -1},
                new Object[]{50, 150, -1},
                new Object[]{150, 150, -1},
                new Object[]{0, 0, 0},
                new Object[]{100, 100, 10000}
        };
    }

    @ParameterizedTest
    @MethodSource("testValues") // Use the method to provide test values
    public void studentScoreCalculator(int mathsScore, int literacyScore, int expectedScore) {
        stundentScoreCalculator.calculateSATScore(mathsScore, literacyScore);
        assertEquals(expectedScore, stundentScoreCalculator.getSatScore());
    }

}