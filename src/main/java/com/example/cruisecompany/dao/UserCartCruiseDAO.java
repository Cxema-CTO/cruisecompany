package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.UserCartCruise;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCartCruiseDAO {

    private static final Logger LOGGER = Logger.getLogger(UserCartCruise.class);

    private static final String HOW_CRUISES_USER_BUY = "SELECT COUNT(*) FROM orders INNER JOIN users  on users.id = orders.client_id WHERE client_id = ?";
    private static final String CRUISE_WHAT_USER_BUY_BY_ORDER_ID = "SELECT orders.id, cruise_id, ship_id, ships.name, route, confirmed, description_eng, description_ukr, duration, price, start_date, end_date, photo, staff, cabins FROM orders INNER JOIN users on users.id = orders.client_id INNER JOIN cruises on cruises.id = orders.cruise_id INNER JOIN ships on ships.id = cruises.ship_id WHERE orders.id = ?";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;


    public static List<UserCartCruise> paginationCruisesUserBuy(int userId, String orderColumn, String direction, int limitRows, int offsetRows) {
        List<UserCartCruise> userCartCruises = new ArrayList<>();
        connection = connectionPool.getConnection();
        if (Objects.equals(orderColumn, "id")) orderColumn = "orders.id";// бікоз використовуєм INNER JOIN

        String PAGINATION_CRUISES = "SELECT orders.id, cruise_id, ship_id, ships.name, route, confirmed, description_eng, description_ukr, duration, price, start_date, end_date, photo, staff, cabins FROM orders INNER JOIN users on users.id = orders.client_id INNER JOIN cruises on cruises.id = orders.cruise_id INNER JOIN ships on ships.id = cruises.ship_id WHERE client_id = ? ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_CRUISES)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserCartCruise cruise = setUserBuyCruise(resultSet);
                userCartCruises.add(cruise);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userCartCruises;
    }

    public static int howMuchCruisesUserBuy(int userId) {
        int answer = 0;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_CRUISES_USER_BUY)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            answer = resultSet.getInt("count");
        } catch (SQLException exception) {
            LOGGER.error(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return answer;
    }


    public static UserCartCruise getOneCruiseWhatUserByFromDB(int orderId) {
        return getCruiseByStringParameter(orderId, CRUISE_WHAT_USER_BUY_BY_ORDER_ID);
    }

    private static UserCartCruise getCruiseByStringParameter(int stringParameter, String sql) {
        connection = connectionPool.getConnection();
        UserCartCruise cruise = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, stringParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cruise = setUserBuyCruise(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return cruise;
    }


    private static UserCartCruise setUserBuyCruise(ResultSet resultSet) throws SQLException {
        UserCartCruise cruise = new UserCartCruise();
        cruise.setOrderId(resultSet.getInt("id"));
        cruise.setCruiseId(resultSet.getInt("cruise_id"));
        cruise.setShipId(resultSet.getInt("ship_id"));
        cruise.setShipName(resultSet.getString("name"));
        cruise.setRoute(resultSet.getString("route"));
        cruise.setConfirmed(resultSet.getBoolean("confirmed"));
        cruise.setDescriptionEng(resultSet.getString("description_eng"));
        cruise.setDescriptionUkr(resultSet.getString("description_ukr"));
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setPrice(resultSet.getInt("price"));
        cruise.setStartCruise(resultSet.getDate("start_date"));
        cruise.setEndCruise(resultSet.getDate("end_date"));
        cruise.setPhotoLink(resultSet.getString("photo"));
        cruise.setStaff(resultSet.getInt("staff"));
        cruise.setCabins(resultSet.getInt("cabins"));
        return cruise;
    }

}
