package com.example.cruisecompany.controller;

import com.example.cruisecompany.dao.FileInfoDAO;
import com.example.cruisecompany.entity.FileInfo;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ImageShowController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ImageShowController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        FileInfo fileInfo = FileInfoDAO.getFileInfoByUserId(userId);
        String hashName = fileInfo.getHashName();
        getImage(response, hashName);
    }

    private void getImage(HttpServletResponse response, String hashName) throws IOException {
        File directory = new File("");
        String link = directory.getAbsolutePath() + File.separator + "Images" + File.separator + hashName;
        Path imageFilePath = Paths.get(link);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(imageFilePath, out);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }
}
