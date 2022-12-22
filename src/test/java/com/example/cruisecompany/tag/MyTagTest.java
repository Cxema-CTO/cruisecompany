package com.example.cruisecompany.tag;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MyTagTest {

    @Test
    public void makeMyTag() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String myTag = "Admin (login: admin, password: admin) Users (password: pass) ©webmaster";
        MyTag testMyTag = new MyTag();
        Method myMethod = MyTag.class.getDeclaredMethod("makeMyTag", null);
        myMethod.setAccessible(true);
        String resultTag = myMethod.invoke(testMyTag).toString();
        assertEquals(myTag, resultTag);
    }
}