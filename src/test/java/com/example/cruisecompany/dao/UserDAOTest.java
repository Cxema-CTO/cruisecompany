package com.example.cruisecompany.dao;

import com.example.cruisecompany.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

//    static UserDAO dao;
//
//    @BeforeEach
//    public void init(){
//        dao = new UserDAO();
//    }

    @Test
    void howMuchIsTheFish() {
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
        User testUser = new User(1,"Admin","21232f297a57a5a743894a0e4a801fc3",true,false,100);

        User user = UserDAO.getUserFromDB("Admin");
        assertEquals(testUser.hashCode(),user.hashCode());
    }
}