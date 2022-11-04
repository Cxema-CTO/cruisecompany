package com.example.cruisecompany.aaaaaTest;

import com.example.cruisecompany.dao.CruiseDAO;
import com.example.cruisecompany.dao.UserCartCruiseDAO;
import com.example.cruisecompany.dao.UserDAO;

import java.sql.SQLException;

public class testDB {
    public static void main(String[] args) throws SQLException {
//        UserDAO.createNewUserInDB("admin","pass",true);
 //       UserDAO.updateUserPasswordInDB("Admin", "admin");
      //  UserDAO.updateUserNameInDB("admin","Admin");
//        UserDAO.banUnbanUserInDB("Bijo",true);
        System.out.println(UserCartCruiseDAO.getOneCruiseWhatUserByFromDB(2));
    }
}
