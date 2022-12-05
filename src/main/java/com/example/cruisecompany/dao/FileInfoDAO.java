package com.example.cruisecompany.dao;

import com.example.cruisecompany.connectionpool.ConnectionPool;
import com.example.cruisecompany.entity.FileInfo;
import com.example.cruisecompany.utils.HashName;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileInfoDAO {

    private static final Logger LOGGER = Logger.getLogger(FileInfoDAO.class);
    private static final String INSERT_NEW_FILE = "INSERT INTO files(file_name,hash_name,user_id) VALUES (?,?,?)";
    private static final String GET_FILE_BY_USERID = "SELECT * FROM files WHERE user_id = ?";


    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection;

    public static void insertFileInfo(int userId, String fileName) {
        connection = connectionPool.getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(INSERT_NEW_FILE);
            ps.setString(1, fileName);
            ps.setString(2, HashName.setHashName(fileName));
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    public static FileInfo getFileInfoByUserId(int userId) {
        connection = connectionPool.getConnection();
        FileInfo fileInfo = null;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(GET_FILE_BY_USERID);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                fileInfo = setFileInfo(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return fileInfo;
    }


    private static FileInfo setFileInfo(ResultSet resultSet) throws SQLException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(resultSet.getInt("id"));
        fileInfo.setFileName(resultSet.getString("file_name"));
        fileInfo.setHashName(resultSet.getString("hash_name"));
        fileInfo.setUserId(resultSet.getInt("user_id"));
        return fileInfo;
    }


}
