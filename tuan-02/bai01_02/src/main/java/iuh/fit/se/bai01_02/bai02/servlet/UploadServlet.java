package iuh.fit.se.bai01_02.bai02.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 25 // 25MB
)
public class UploadServlet extends HttpServlet {
    private String uploadPathToSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        uploadPathToSource = "F:\\java\\lap-trinh-www-java\\labs\\tuan-02\\bai01_02\\src\\main\\webapp\\uploads\\";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Part> filesParts = req.getParts();
        for (Part part : filesParts) {


        }
    }
}