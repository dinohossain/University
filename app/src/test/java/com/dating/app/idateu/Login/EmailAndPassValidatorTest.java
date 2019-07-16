package com.dating.app.idateu.Login;

import com.dating.app.idateu.SignUp_LogIn.SignUpLogIn;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.*;

public class EmailAndPassValidatorTest
    {
    SignUpLogIn target;
    Method Emailmethod, Passwordmethod;

    @Before
    public void setUp()
        {
        System.out.println("Finding the signup/login class");
        target = new SignUpLogIn();
        }

    @Test
    public void testEmailRegex() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
        {
        String email;
        Boolean answer;

        System.out.println("Preparing for emailRegex test");
        Emailmethod = SignUpLogIn.class.getDeclaredMethod("isEmailValid", String.class);
        Emailmethod.setAccessible(true);

        email = "testTestgmail.com";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertFalse(answer);

        email = "test@gmail.com";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertTrue(answer);

        email = "589@gmail.com";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertTrue(answer);

        email = "test@gmail";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertFalse(answer);

        email = "@hotmail";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertFalse(answer);

        email = "t@g.h";
        answer = (boolean)Emailmethod.invoke(target,email);
        assertTrue(answer);

        System.out.println("Finished emailRegex test");
        }


    @Test
    public void testPassword() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
        {
        String pass;
        Boolean answer;

        System.out.println("Preparing for password test");
        Passwordmethod = SignUpLogIn.class.getDeclaredMethod("didUserTypeInPassword", String.class);
        Passwordmethod.setAccessible(true);

        pass = "hhkhj";
        answer = (boolean)Passwordmethod.invoke(target,pass);
        assertTrue(answer);

        pass = " ";
        answer = (boolean)Passwordmethod.invoke(target,pass);
        assertFalse(answer);

        pass = "  ";
        answer = (boolean)Passwordmethod.invoke(target,pass);
        assertFalse(answer);

        pass = " p  w"; //password cannot have spaces in them
        answer = (boolean)Passwordmethod.invoke(target,pass);
        assertFalse(answer);

        System.out.println("Finished password test");
        }

    }
