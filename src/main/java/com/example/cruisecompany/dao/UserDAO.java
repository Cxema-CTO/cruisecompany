package com.example.cruisecompany.dao;


import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.User;
import com.example.cruisecompany.password.EncodePassword;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String HOW_USERS = "SELECT COUNT(*) FROM users";
    private static final String GET_ONE_BY_USERNAME = "SELECT * FROM users WHERE user_name = ?";
    private static final String CREATE_NEW_USER = "INSERT INTO users (user_name, password,is_admin) VALUES (?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_name = ?";
    private static final String UPDATE_USER_USERNAME = "UPDATE users SET user_name = ? WHERE user_name = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE user_name = ?";
    private static final String UPDATE_USER_BANNED = "UPDATE users SET is_banned = ? WHERE user_name = ?";
    private static final String UPDATE_USER_MONEY = "UPDATE users SET balance = ? WHERE user_name = ?";


    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;


    public static int howMuchIsTheFish() {
        int answer = 0;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            answer = resultSet.getInt("count");
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return answer;
    }


    public static List<User> paginationUsers(String orderColumn, String direction, int limitRows, int offsetRows) {
        List<User> userList = new ArrayList<>();
        connection = connectionPool.getConnection();

        String PAGINATION_USERS = "SELECT * FROM users ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }


    public static List<User> getAllUsers() {
        connection = connectionPool.getConnection();
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUser(resultSet);
                userList.add(user);
            }
//            userList.sort(Comparator.comparing(User::getUserName));
            userList.sort(Comparator.comparing(User::getId));
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }


    public static void updateUserNameInDB(String hasUserName, String setUserName) {
        if (assertHasUserInDBbyUserName(hasUserName)) {
            if (!assertHasUserInDBbyUserName(setUserName)) {
                connection = connectionPool.getConnection();
                try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_USERNAME)) {
                    ps.setString(1, setUserName);
                    ps.setString(2, hasUserName);
                    ps.executeUpdate();
                } catch (SQLException exception) {
                    LOGGER.error(exception, exception);
                } finally {
                    connectionPool.releaseConnection(connection);
                }
            } else {
                System.out.println("Username is already taken, choose another username");
            }
        }
    }


    public static void updateUserPasswordInDB(String userName, String newPassword) {
        if (assertHasUserInDBbyUserName(userName)) {
            connection = connectionPool.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
                ps.setString(1, EncodePassword.getHashPassword(newPassword));
                ps.setString(2, userName);
                ps.executeUpdate();
            } catch (SQLException exception) {
                LOGGER.error(exception, exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static void banUnbanUserInDB(String userName, Boolean isBanned) {
        if (assertHasUserInDBbyUserName(userName)) {
            connection = connectionPool.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BANNED)) {
                ps.setBoolean(1, isBanned);
                ps.setString(2, userName);
                ps.executeUpdate();
            } catch (SQLException exception) {
                LOGGER.error(exception, exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static void setMoneyUserInDB(String userName, int howMuch) {
        if (assertHasUserInDBbyUserName(userName)) {
            connection = connectionPool.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_MONEY)) {
                ps.setInt(1, howMuch);
                ps.setString(2, userName);
                ps.executeUpdate();
            } catch (SQLException exception) {
                LOGGER.error(exception, exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    public static void deleteUserInDB(String userName) {
        if (assertHasUserInDBbyUserName(userName)) {
            connection = connectionPool.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
                ps.setString(1, userName);
                ps.executeUpdate();
            } catch (SQLException exception) {
                LOGGER.error(exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }
    }


    public static void createNewUserInDB(String userName, String password, Boolean isAdmin) throws SQLException {
        if (!assertHasUserInDBbyUserName(userName)) {
            PreparedStatement ps = connection.prepareStatement(CREATE_NEW_USER);
            ps.setString(1, userName);
            ps.setString(2, EncodePassword.getHashPassword(password));
            ps.setBoolean(3, isAdmin);
            ps.executeUpdate();
        } else {
            LOGGER.error("Can't create, user with this username exists, choose another username");
        }
    }


    public static Boolean assertHasUserInDBbyUserName(String userName) {
        User checkUserInDB = UserDAO.getUserFromDB(userName);
        boolean answer = false;
        if (checkUserInDB != null) {
            if (checkUserInDB.getUserName().equals(userName)) {
                answer = true;
            }
        }
        return answer;
    }


    public static User getUserFromDB(String userName) {
        return getUserByStringParameter(userName, GET_ONE_BY_USERNAME);
    }

    private static User getUserByStringParameter(String stringParameter, String sql) {
        connection = connectionPool.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, stringParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = setUser(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }


    private static User setUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setAdmin(resultSet.getBoolean("is_admin"));
        user.setBanned(resultSet.getBoolean("is_banned"));
        user.setAccountBalance(Integer.parseInt(resultSet.getString("balance")));
        return user;
    }


}
