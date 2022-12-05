package com.example.cruisecompany.controller;

import com.example.cruisecompany.dao.FileInfoDAO;
import com.example.cruisecompany.entity.FileInfo;
import com.example.cruisecompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ImageSaveController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ImageSaveController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Part filePart = req.getPart("photo_passport");

        saveUserPassportPhoto(filePart, userFromSession);

        resp.sendRedirect("startApp.jsp");
    }

    private void saveUserPassportPhoto(Part filePart, User user) throws IOException {
        String fileName = filePart.getSubmittedFileName();
        long fileSize = filePart.getSize();
        int userId = user.getId();

        FileInfoDAO.insertFileInfo(userId, fileName);

        FileInfo fileInfo = FileInfoDAO.getFileInfoByUserId(userId);
        String hashName = fileInfo.getHashName();

        File directory = new File("Images");
        Path path = Paths.get(directory.getAbsolutePath());
        if (!directory.exists()) Files.createDirectory(path);
        InputStream fileContent = filePart.getInputStream();
        path = Paths.get(directory.getAbsolutePath() + File.separator + hashName);
        Files.copy(fileContent, path);

        LOGGER.info(directory.getAbsolutePath());//////////////////////////////////////////
        LOGGER.info("fileName: " + fileName + ", fileSize: " + fileSize + " bytes");///////
         }
}
