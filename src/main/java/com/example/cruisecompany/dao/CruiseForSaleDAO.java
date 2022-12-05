package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.entity.CruiseForSale;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CruiseForSaleDAO {
    private static final Logger LOGGER = Logger.getLogger(CruiseForSaleDAO.class);
    private static final String HOW_CRUISES_FOR_SALE = "SELECT COUNT(*) FROM cruises INNER JOIN ships on ships.id = cruises.ship_id";
    private static String patternForPaginationFilter;
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;


    public static int howMuchIsTheCruisesForSale() {
        int answer = 0;
        patternForPaginationFilter = " WHERE start_date > '" + Pagination.from + "' AND end_date < '" + Pagination.to + "' AND duration BETWEEN " + Pagination.durationMin + " AND " + Pagination.durationMax;

        connection = connectionPool.getConnection();
        if (Objects.equals(Pagination.filter, "on")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_CRUISES_FOR_SALE + patternForPaginationFilter)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                answer = resultSet.getInt("count");
            } catch (SQLException exception) {
                LOGGER.error(exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }

        if (Objects.equals(Pagination.filter, "off")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_CRUISES_FOR_SALE)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                answer = resultSet.getInt("count");
            } catch (SQLException exception) {
                LOGGER.error(exception, exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }


        return answer;
    }

    public static List<CruiseForSale> paginationCruiseForSale(String orderColumn, String direction, int limitRows, int offsetRows) {
        List<CruiseForSale> cruiseListForSale = new ArrayList<>();
        connection = connectionPool.getConnection();
        patternForPaginationFilter = " WHERE start_date > '" + Pagination.from + "' AND end_date < '" + Pagination.to + "' AND duration BETWEEN " + Pagination.durationMin + " AND " + Pagination.durationMax;

        if (Objects.equals(orderColumn, "id")) orderColumn = "cruises.id";// бікоз використовуєм INNER JOIN
        String PAGINATION_CRUISES = "SELECT * FROM cruises INNER JOIN ships  on ships.id = cruises.ship_id ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        if (Pagination.filter == "on")
            PAGINATION_CRUISES = "SELECT * FROM cruises INNER JOIN ships  on ships.id = cruises.ship_id " + patternForPaginationFilter + " ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;


        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_CRUISES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CruiseForSale cruiseForSale = setCruiseForSale(resultSet);
                cruiseListForSale.add(cruiseForSale);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return cruiseListForSale;
    }

    private static CruiseForSale setCruiseForSale(ResultSet resultSet) throws SQLException {
        CruiseForSale cruise = new CruiseForSale();
        cruise.setId(resultSet.getInt("id"));
        cruise.setShipId(resultSet.getInt("ship_id"));
        cruise.setShipName(resultSet.getString("name"));
        cruise.setStartCruise(resultSet.getDate("start_date"));
        cruise.setEndCruise(resultSet.getDate("end_date"));
        cruise.setDuration(resultSet.getInt("duration"));
        cruise.setDescriptionEng(resultSet.getString("description_eng"));
        cruise.setDescriptionUkr(resultSet.getString("description_ukr"));
        cruise.setRoute(resultSet.getString("route"));
        cruise.setPhotoLink(resultSet.getString("photo"));
        cruise.setPrice(resultSet.getInt("price"));
        int cabinsOnShip = resultSet.getInt("cabins");
        int cabinsSold = resultSet.getInt("cabins_sold");
        cruise.setCabinsLeft(cabinsOnShip - cabinsSold);
        return cruise;
    }
}
