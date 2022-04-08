package co.edu.unbosque.taller_3;

import java.io.*;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "uploadFile", value = "/upload-file")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadFile extends HttpServlet {
    private String UPLOAD_DIRECTORY = "uploads";

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Extracting parameters other than uploaded file
        System.out.println("Name: " + request.getParameter("name"));

        // Getting an instance of the upload path
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        // If path doesn`t exist, create it
        if (!uploadDir.exists()) uploadDir.mkdir();

        try {
            // Getting each part from the request
            for (Part part : request.getParts()) {
                // Storing the file using the same name
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Redirecting
        response.sendRedirect("./result.html");
    }

    public void destroy() {}
}
