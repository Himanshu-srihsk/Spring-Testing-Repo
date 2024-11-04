package com.springtest.demo.service;

import com.springtest.demo.config.AppConfig;
import com.springtest.demo.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
class CalculatorServiceTest {
    @Autowired
    private CalculatorService calculatorService;

//    @BeforeEach
//    public void init(){
//        calculatorService = new CalculatorService();
//    }

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
    }

    @Test
    void testArrayEquals() {
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray, "The arrays should be equal");
        assertFalse(Arrays.equals(new int[]{1,2,3,4}, actualArray), "The arrays should not be equal");
    }



    @Test
    void testDivideValidInputs() {
        double result = calculatorService.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    void testValidInput() {
        assertThrows(InvalidInputException.class, () -> {
            calculatorService.checkForInput("dog");
        });
    }

    @Test
    void testDivideByZeroThrowsException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(10, 0);
        });

        assertEquals("Denominator cannot be zero.", thrown.getMessage());
    }

    @Test
    public void squaredIntegerHappyPath(){


        assertEquals(9, calculatorService.squareInteger(3));
        assertEquals(100, calculatorService.squareInteger(10));

    }

    @Test
    public void squaredIntegerUpperBoundary(){

       assertThrows(RuntimeException.class, () -> {
           calculatorService.squareInteger(50000);
        });

    }

    public void squaredIntegerLowerBoundary(){

        assertThrows(RuntimeException.class, () -> {
            calculatorService.squareInteger(-50000);
        });

    }

    @Test
    public void squaredIntegerNull(){

        assertEquals(0,calculatorService.squareInteger(null));


    }

    @Test
    public void squaredIntegerNegative(){
      assertEquals(25,calculatorService.squareInteger(-5));
    }


    public void squaredIntegerNaN(){
        assertThrows(RuntimeException.class, () -> {
            calculatorService.squareInteger((int)Math.sqrt(-5));
        });
    }

    //Testing private methods
    @Test
    public void ComputerGradeWithReflection() throws Exception{
        Class[] parameters = new Class[1];
        parameters[0] = Integer.class;
        Method method = CalculatorService.class.getDeclaredMethod("computeGrade", parameters);
        method.setAccessible(true);

        Object[] methodArgs = new Object[1];
        methodArgs[0] =67;
        String actualGrade = (String) method.invoke(calculatorService,methodArgs);
        assertEquals("fail", actualGrade);
    }
    //Testing private methods
    @Test
    public void ComputerGradeWithPowermock() throws Exception{
        String actualGrade = ReflectionTestUtils.invokeMethod(calculatorService, "computeGrade", 67);
        assertEquals("fail", actualGrade);
    }

}
