package com.example.cruisecompany.dao;

import com.example.cruisecompany.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;

class UserDAOTest {

    @Test
    void howMuchIsTheFish() {
        try (MockedStatic<UserDAO> dao = mockStatic(UserDAO.class)) {
            dao.when(UserDAO::howMuchIsTheFish).thenReturn(222);
            int usersQuantity = UserDAO.howMuchIsTheFish();
            System.out.println(usersQuantity);
            assertEquals(222, usersQuantity);
        }
    }

    @Test
    void howMuchIsTheFishWithoutTry() {
        MockedStatic<UserDAO> dao = mockStatic(UserDAO.class);
        dao.when(UserDAO::howMuchIsTheFish).thenReturn(777);
        int usersQuantity = UserDAO.howMuchIsTheFish();
        System.out.println(usersQuantity);
        assertEquals(777, usersQuantity);
    }


    @Test
    void paginationUsers() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUserNameInDB() {
    }

    @Test
    void updateUserPasswordInDB() {
    }

    @Test
    void banUnbanUserInDB() {
    }

    @Test
    void setMoneyUserInDB() {
    }

    @Test
    void deleteUserInDB() {
    }

    @Test
    void createNewUserInDB() {
    }

    @Test
    void assertHasUserInDBbyUserName() {
    }

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
}