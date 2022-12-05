package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.Cruise;
import com.example.cruisecompany.entity.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private static final String HOW_ORDERS = "SELECT COUNT(*) FROM orders";
    private static final String GET_ONE_BY_ORDER_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String CREATE_NEW_ORDER = "INSERT INTO orders (client_id, cruise_id, paid, confirmed) VALUES (?,?,?,?)";
    private static final String UPDATE_ORDER_CONFIRMED = "UPDATE orders SET confirmed = ? WHERE id = ?";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;



    public static void updateOrderConfirmedInDB(int orderId, Boolean isConfirmed) {
            connection = connectionPool.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_CONFIRMED)) {
                ps.setBoolean(1, isConfirmed);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            } catch (SQLException exception) {
                LOGGER.error(exception);
            } finally {
                connectionPool.releaseConnection(connection);
            }
    }

    public static void createNewOrderInDB(int clientId, int cruiseId, boolean paid, boolean confirmed) {
        connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ORDER)) {
            ps.setInt(1, clientId);
            ps.setInt(2, cruiseId);
            ps.setBoolean(3, paid);
            ps.setBoolean(4, confirmed);
            ps.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static List<Order> paginationOrders(String orderColumn, String direction, int limitRows, int offsetRows) {
        List<Order> orderList = new ArrayList<>();
        connection = connectionPool.getConnection();

        String PAGINATION_ORDERS = "SELECT * FROM orders ORDER BY " + orderColumn + " " + direction + " LIMIT " + limitRows + " OFFSET " + offsetRows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PAGINATION_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = setOrder(resultSet);
                orderList.add(order);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return orderList;
    }

    public static int howMuchIsTheOrders() {
        int answer = 0;
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(HOW_ORDERS)) {
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

    public static Order getOrderFromDB(int cruiseId) {
        return getOrderByStringParameter(cruiseId, GET_ONE_BY_ORDER_ID);
    }


    private static Order getOrderByStringParameter(int stringParameter, String sql) {
        connection = connectionPool.getConnection();
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, stringParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = setOrder(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return order;
    }

    private static Order setOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setClientId(resultSet.getInt("client_id"));
        order.setCruiseId(resultSet.getInt("cruise_id"));
        order.setPaid(resultSet.getBoolean("paid"));
        order.setConfirmed(resultSet.getBoolean("confirmed"));
        return order;
    }
}
