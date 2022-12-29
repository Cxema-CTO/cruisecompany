package com.example.cruisecompany.dao;

import com.example.cruisecompany.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;

class UserDAOTest {


    @Test
    void getUserFromDB() {
        User testUser = new User(1, "Admin", "21232f297a57a5a743894a0e4a801fc3", true, false, 100);
        User user = UserDAO.getUserFromDB("Admin");
        assertEquals(testUser.hashCode(), user.hashCode());
    }

    @Test
    void getUserFromDBisNull() {
        User user = UserDAO.getUserFromDB("superAdmin");
        assertNull(user);
    }

    @Test
    void howMuchIsTheFish() {
        try (MockedStatic<UserDAO> dao = mockStatic(UserDAO.class)) {
            dao.when(UserDAO::howMuchIsTheFish).thenReturn(222);
            int usersQuantity = UserDAO.howMuchIsTheFish();
            assertEquals(222, usersQuantity);
        }
    }

    @Test
    void howMuchIsTheFishWithoutTry() {
        MockedStatic<UserDAO> dao = mockStatic(UserDAO.class);
        dao.when(UserDAO::howMuchIsTheFish).thenReturn(777);
        int usersQuantity = UserDAO.howMuchIsTheFish();
        assertEquals(777, usersQuantity);
        dao.close();// without try, MUST CLOSE ! ! ! ! !
    }


    @Test
    void assertHasUserInDBbyUserNameTrue() {
        assertEquals(true, UserDAO.assertHasUserInDBbyUserName("Admin"));
    }

    @Test
    void assertHasUserInDBbyUserNameFalse() {
        assertEquals(false, UserDAO.assertHasUserInDBbyUserName("SuperAdmin"));
    }


}