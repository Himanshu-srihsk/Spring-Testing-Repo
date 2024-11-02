package com.springtest.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CalculatorServiceTest {
    @Autowired
  private CalculatorService calculatorService;
    @Test
    void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(5, 3);
        assertNotEquals(1, result, "5 - 3 should not equal 1");
    }

    @Test
    void testIsEven() {
        assertTrue(calculatorService.isEven(4), "4 should be even");
        assertFalse(calculatorService.isEven(3), "3 should not be even");
    }

    @Test
    void testGetMessage() {
        assertNull(calculatorService.getMessage(), "Message should be null");
    }

    @Test
    void testSameObject() {
        CalculatorService sameInstance = calculatorService;
        assertSame(calculatorService, sameInstance, "The instances should be the same");

        CalculatorService newInstance = new CalculatorService();
        assertNotSame(calculatorService, newInstance, "The instances should not be the same");
    }

    @Test
    void testArrayEquals() {
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray, "The arrays should be equal");
        assertFalse(Arrays.equals(new int[]{1,2,3,4}, actualArray), "The arrays should not be equal");
    }
}