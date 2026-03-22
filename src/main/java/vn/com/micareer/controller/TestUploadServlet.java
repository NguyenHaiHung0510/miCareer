/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package vn.com.micareer.controller;

import vn.com.micareer.util.FileUploadUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet("/test-upload")
public class TestUploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("cvFile");
        if(FileUploadUtil.isValidCvFile(filePart)){
            String uploadPath = getServletContext().getRealPath("");

            String savedPath = FileUploadUtil.saveFile(filePart, uploadPath);

            response.getWriter().println("Saved path: " + savedPath);
        }
        else{
            response.getWriter().println("Invalid file format!");
        }
    }
}