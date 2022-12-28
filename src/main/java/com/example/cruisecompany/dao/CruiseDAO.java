package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.Cruise;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CruiseDAO {
    private static final Logger LOGGER = Logger.getLogger(CruiseDAO.class);
    private static final String HOW_CRUISES = "SELECT COUNT(*) FROM cruises";
    private static final String GET_ONE_BY_CRUISE_ID = "SELECT * FROM cruises WHERE id = ?";
    private static final String CREATE_NEW_CRUISE = "INSERT INTO cruises (ship_id,cabins_sold , start_date,end_date,duration,route,price) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_CABINS_SOLD = "UPDATE cruises SET cabins_sold = ? WHERE id = ?";


    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;


    public static void setCabinsSoldInDB(int cruiseId, int howMuch) {
        connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CABINS_SOLD)) {
            ps.setInt(1, howMuch);
            ps.setInt(2, cruiseId);
            ps.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static List<Cruise> paginationCruise(String orderColumn, String direction, int limitRows, int offsetRows) {
        List<Cruise> cruiseList = new ArrayList<>();
        connection = connectionPool.getConnection();

        String PAGINATION_CRUISES = "SELECT * FROM cruises ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_CRUISES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cruise cruise = setCruise(resultSet);
                cruiseList.add(cruise);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return cruiseList;
    }


    public static int howMuchIsTheCruises() {
        int answer = 0;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_CRUISES)) {
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

    public static void createNewCruiseInDB(int shipId, int soldCabins, java.sql.Date start, java.sql.Date end, int duration, String route, int price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CRUISE);
        ps.setInt(1, shipId);
        ps.setInt(2, soldCabins);
        ps.setDate(3, start);
        ps.setDate(4, end);
        ps.setInt(5, duration);
        ps.setString(6, route);
        ps.setInt(7, price);
        ps.executeUpdate();
    }


    public static Cruise getCruiseFromDB(int cruiseId) {
        return getCruiseByStringParameter(cruiseId, GET_ONE_BY_CRUISE_ID);
    }

    private static Cruise getCruiseByStringParameter(int stringParameter, String sql) {
        connection = connectionPool.getConnection();
        Cruise cruise = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, stringParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cruise = setCruise(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return cruise;
    }

    private static Cruise setCruise(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getInt("id"));
        cruise.setShipId(resultSet.getInt("ship_id"));
        cruise.setCabinsSold(resultSet.getInt("cabins_sold"));
        cruise.setStartCruise(resultSet.getDate("start_date"));
        cruise.setEndCruise(resultSet.getDate("end_date"));
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setRoute(resultSet.getString("route"));
        cruise.setCruisePrice(resultSet.getInt("price"));
        return cruise;
    }
}
