package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.Ship;
import com.example.cruisecompany.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShipDAO {
    private static final Logger LOGGER = Logger.getLogger(ShipDAO.class);
    private static final String HOW_SHIPS = "SELECT COUNT(*) FROM ships";
    private static final String CREATE_NEW_SHIP = "INSERT INTO ships (name, cabins,description_eng, description_ukr,photo,staff) VALUES (?,?,?,?,?,?)";
    private static final String GET_ALL_SHIPS = "SELECT * FROM ships";
    private static final String GET_ONE_BY_SHIPNAME = "SELECT * FROM ships WHERE name = ?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;



    public static List<Ship> getAllShipsFromDB() {
        connection = connectionPool.getConnection();
        List<Ship> shipList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SHIPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ship ship = setShip(resultSet);
                shipList.add(ship);
            }
            shipList.sort(Comparator.comparing(Ship::getShipName));
        } catch (SQLException exception) {
            LOGGER.error(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return shipList;
    }
    public static List<Ship> paginationShips(String orderColumn, String direction, int limitRows, int offsetRows) {
        List<Ship> shipList = new ArrayList<>();
        connection = connectionPool.getConnection();

        String PAGINATION_SHIPS = "SELECT * FROM ships ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_SHIPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ship ship = setShip(resultSet);
                shipList.add(ship);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return shipList;
    }

    public static int howMuchIsTheShips() {
        int answer = 0;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_SHIPS)) {
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

    public static void createNewShipInDB(String shipName, int cabins, String descriptionEng, String descriptionUkr, String photoLink, int staff) throws SQLException {
        if (!assertHasShipInDBbyShipName(shipName)) {
            PreparedStatement ps = connection.prepareStatement(CREATE_NEW_SHIP);
            ps.setString(1, shipName);
            ps.setInt(2, cabins);
            ps.setString(3, descriptionEng);
            ps.setString(4, descriptionUkr);
            ps.setString(5, photoLink);
            ps.setInt(6, staff);
            ps.executeUpdate();
        } else {
            System.out.println("Can't create, ship with this shipname exists, choose another shipname");
        }
    }

    public static Boolean assertHasShipInDBbyShipName(String shipName) {
        Ship checkShipInDB = ShipDAO.getShipFromDB(shipName);
        boolean answer = false;
        if (checkShipInDB != null) {
            if (checkShipInDB.getShipName().equals(shipName)) {
                answer = true;
            }
        }
        return answer;
    }

    public static Ship getShipFromDB(String shipName) {
        return getShipByStringParameter(shipName, GET_ONE_BY_SHIPNAME);
    }

    private static Ship getShipByStringParameter(String stringParameter, String sql) {
        connection = connectionPool.getConnection();
        Ship ship = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, stringParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ship = setShip(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return ship;
    }

    private static Ship setShip(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getInt("id"));
        ship.setShipName(resultSet.getString("name"));
        ship.setCabins(resultSet.getInt("cabins"));
        ship.setDescriptionEng(resultSet.getString("description_eng"));
        ship.setDescriptionUkr(resultSet.getString("description_ukr"));
        ship.setPhotoLink(resultSet.getString("photo"));
        ship.setStaff(resultSet.getInt("staff"));
        return ship;
    }
}
