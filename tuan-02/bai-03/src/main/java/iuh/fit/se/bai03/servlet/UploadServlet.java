package iuh.fit.se.bai03.servlet;

import iuh.fit.se.bai03.Contact;
import iuh.fit.se.bai03.dao.ContactDAO;
import iuh.fit.se.bai03.service.Database;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

@WebServlet(name = "uploadServlet", value = "/upload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class UploadServlet extends HttpServlet {
    private ContactDAO contactDAO = new ContactDAO();
    private String uploadPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Create uploads directory in webapp folder
        uploadPath = getServletContext().getRealPath("/uploads/");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        // Fix parameter names to match HTML form
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String message = null;

        if (firstName == null || firstName.trim().isEmpty() || 
            lastName == null || lastName.trim().isEmpty()) {
            message = "ERROR: First name and last name are required";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/message").forward(req, resp);
            return;
        }

        Part filePart = req.getPart("photo");
        String fileName = "";
        
        if (filePart != null && filePart.getSubmittedFileName() != null && 
            !filePart.getSubmittedFileName().isEmpty()) {
            fileName = filePart.getSubmittedFileName();
        }

        try (Connection connection = Database.getConnection()) {
            // Create Blob from InputStream
            Blob photoBlob = null;
            if (filePart != null && filePart.getSize() > 0) {
                photoBlob = connection.createBlob();
                try (InputStream inputStream = filePart.getInputStream();
                     OutputStream blobOutputStream = photoBlob.setBinaryStream(1)) {
                    
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        blobOutputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            Contact contact = new Contact();
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPhoto(photoBlob);

            boolean success = contactDAO.insertContact(contact);
            
            if (success) {
                message = "Contact uploaded successfully!";
                
                // Save file to disk if photo was uploaded
                if (photoBlob != null && !fileName.isEmpty()) {
                    String filePath = uploadPath + fileName;
                    try (InputStream blobInputStream = photoBlob.getBinaryStream();
                         FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                        
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = blobInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    message += " File saved to: " + filePath;
                }
            } else {
                message = "ERROR: Failed to insert contact into database";
            }
            
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }

        req.setAttribute("message", message);
        req.getRequestDispatcher("/message").forward(req, resp);
    }
}
