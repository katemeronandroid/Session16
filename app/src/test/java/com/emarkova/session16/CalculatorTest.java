package com.emarkova.session16;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testCompute() {
        Calculator calculator = new Calculator();
        assertEquals("6", calculator.compute("1+2*3-1"));
        assertNotSame("0", calculator.compute("1*2+3"));
    }

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(new Double(4), calculator.add(2.0,2.0));
        assertEquals(new Double(5), calculator.add(-1.0,6.0));
        assertNotSame(new Double(10), calculator.add(11.0,1.0));
    }

    @Test
    public void testSub() {
        Calculator calculator = new Calculator();
        assertEquals(new Double(-2), calculator.sub(0.0,2.0));
        assertNotSame(new Double(0), calculator.sub(11.0,1.0));
    }

    @Test
    public void testMul() {
        Calculator calculator = new Calculator();
        assertEquals(new Double(0), calculator.mul(0.0,2.0));
        assertEquals(new Double(-1), calculator.mul(-1.0,1.0));
        assertEquals(new Double(4), calculator.mul(-2.0,-2.0));
        assertNotSame(new Double(0), calculator.sub(11.0,1.0));
    }

    @Test
    public void testDiv() {
        Calculator calculator = new Calculator();
        assertEquals(new Double(0), calculator.div(0.0, 2.0));
        assertEquals(new Double(2), calculator.div(4.0, 2.0));
        assertEquals(new Double(1), calculator.div(-2.0, -2.0));
        assertNotSame(new Double(0), calculator.div(11.0, 1.0));
    }

    @Test(expected=ArithmeticException.class)
     public void testDivZero() {
        Calculator calculator = new Calculator();
        calculator.div(11.0, 0.0);
    }

    @Test
    public void testGetString() {
        Class factoryClass = Calculator.class;
        String result = "";
        try {
            Method method = factoryClass.getDeclaredMethod("getString", String.class);
            method.setAccessible(true);
            Calculator calculator = new Calculator();
            result = (String)method.invoke(calculator,"10.0");
            method.setAccessible(false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        assertEquals("10", result);
    }
}
