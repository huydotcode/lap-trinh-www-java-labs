package iuh.fit.se.bai01_02.bai02.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
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
        // Create upload directory if it doesn't exist
        File uploadDir = new File(uploadPathToSource);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create upload directory");
                return;
            }
        }

        Collection<Part> fileParts = req.getParts();
        ArrayList<String> uploadedFiles = new ArrayList<>();

        for (Part part : fileParts) {
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                String fileName = part.getSubmittedFileName();

                if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
                    continue;
                }

                String originalFileName = fileName;
                int counter = 1;
                while (new File(uploadPathToSource + fileName).exists()) {
                    int dotIndex = originalFileName.lastIndexOf('.');
                    if (dotIndex > 0) {
                        String name = originalFileName.substring(0, dotIndex);
                        String extension = originalFileName.substring(dotIndex);
                        fileName = name + "_" + counter + extension;
                    } else {
                        fileName = originalFileName + "_" + counter;
                    }
                    counter++;
                }

                String filePath = uploadPathToSource + fileName;

                try (InputStream inputStream = part.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(filePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    long totalBytes = 0;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytes += bytesRead;
                    }

                    uploadedFiles.add(fileName);
                    System.out.println("File uploaded successfully: " + fileName + " (" + totalBytes + " bytes)");
                } catch (IOException e) {
                    System.err.println("Error uploading file: " + originalFileName);
                    e.printStackTrace();
                }
            }
        }

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Uploaded files</h1>");
        writer.println("<ul>");
        for (String fileName : uploadedFiles) {
            writer.println("<li>" + fileName + "</li>");
        }
        writer.println("</ul>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}