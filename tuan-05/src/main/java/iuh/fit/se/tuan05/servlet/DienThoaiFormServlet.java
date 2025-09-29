package iuh.fit.se.tuan05.servlet;

import iuh.fit.se.tuan05.dao.DienThoaiDAO;
import iuh.fit.se.tuan05.dao.NhaCungCapDAO;
import iuh.fit.se.tuan05.model.DienThoai;
import iuh.fit.se.tuan05.model.NhaCungCap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servlet để xử lý form thêm điện thoại với upload hình ảnh
 * 
 * @author Student
 */
@WebServlet("/DienThoaiFormServlet")
@MultipartConfig(maxFileSize = 5242880) // 5MB max file size
public class DienThoaiFormServlet extends HttpServlet {
    
    private DienThoaiDAO dienThoaiDAO;
    private NhaCungCapDAO nhaCungCapDAO;
    
    // Regular expressions for validation
    private static final Pattern YEAR_PATTERN = Pattern.compile("^(19|20)\\d{2}$");
    private static final Pattern CONFIG_PATTERN = Pattern.compile("^.{1,255}$");
    private static final Pattern FILE_EXTENSION_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png)$", Pattern.CASE_INSENSITIVE);
    
    @Override
    public void init() throws ServletException {
        super.init();
        dienThoaiDAO = new DienThoaiDAO();
        nhaCungCapDAO = new NhaCungCapDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Thiết lập currentPage cho navbar
            request.setAttribute("currentPage", "add");
            request.setAttribute("pageTitle", "Thêm Điện Thoại");
            
            // Lấy danh sách tất cả NCC để hiển thị dropdown
            List<NhaCungCap> allNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
            request.setAttribute("allNhaCungCap", allNhaCungCap);
            
            // Forward đến JSP form
            request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải form: " + e.getMessage());
            request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Thiết lập currentPage cho navbar
            request.setAttribute("currentPage", "add");
            request.setAttribute("pageTitle", "Thêm Điện Thoại");
            
            // Lấy danh sách NCC để hiển thị dropdown
            List<NhaCungCap> allNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
            request.setAttribute("allNhaCungCap", allNhaCungCap);
            
            // Lấy và validate form data
            String maDT = request.getParameter("maDT");
            String tenDT = request.getParameter("tenDT");
            String namSanXuatStr = request.getParameter("namSanXuat");
            String cauHinh = request.getParameter("cauHinh");
            String maNCC = request.getParameter("maNCC");
            
            // Validate required fields
            if (maDT == null || maDT.trim().isEmpty() ||
                tenDT == null || tenDT.trim().isEmpty() ||
                namSanXuatStr == null || namSanXuatStr.trim().isEmpty() ||
                cauHinh == null || cauHinh.trim().isEmpty() ||
                maNCC == null || maNCC.trim().isEmpty()) {
                
                request.setAttribute("error", "Tất cả các trường thông tin đều bắt buộc!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            // Validate mã điện thoại format
            if (!maDT.matches("DT\\d{3}")) {
                request.setAttribute("error", "Mã điện thoại phải có định dạng DT001, DT002, ...");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            // Validate năm sản xuất
            int namSanXuat;
            try {
                namSanXuat = Integer.parseInt(namSanXuatStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Năm sản xuất phải là số nguyên!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            if (!YEAR_PATTERN.matcher(namSanXuatStr).matches()) {
                request.setAttribute("error", "Năm sản xuất phải là số có 4 chữ số từ 1900 đến 2099!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            // Validate cấu hình không quá 255 ký tự
            if (!CONFIG_PATTERN.matcher(cauHinh).matches()) {
                request.setAttribute("error", "Thông tin cấu hình không được vượt quá 255 ký tự!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            // Kiểm tra mã điện thoại đã tồn tại chưa
            if (dienThoaiDAO.existsDienThoai(maDT.trim())) {
                request.setAttribute("error", "Mã điện thoại " + maDT + " đã tồn tại trong hệ thống!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                return;
            }
            
            // Handle file upload
            String fileName = null;
            Part filePart = request.getPart("hinhAnh");
            if (filePart != null && filePart.getSize() > 0) {
                fileName = filePart.getSubmittedFileName();
                
                // Validate file type
                if (!FILE_EXTENSION_PATTERN.matcher(fileName).matches()) {
                    request.setAttribute("error", "Chỉ chấp nhận file ảnh PNG, JPG, JPEG!");
                    request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                    return;
                }
                
                // Validate file size (5MB max)
                if (filePart.getSize() > 5242880) {
                    request.setAttribute("error", "Kích thước file không được vượt quá 5MB!");
                    request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
                    return;
                }
                
                // Generate unique filename
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                fileName = "phone_" + System.currentTimeMillis() + extension;
                
                // Save file to images folder
                Path uploadPath = Paths.get(getServletContext().getRealPath("/images"));
                Files.createDirectories(uploadPath);
                
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            
            // Tạo DienThoai object
            DienThoai dienThoai = new DienThoai(
                maDT.trim(),
                tenDT.trim(),
                namSanXuat,
                cauHinh.trim(),
                maNCC.trim(),
                fileName
            );
            
            // Thêm vào database
            boolean success = dienThoaiDAO.addDienThoai(dienThoai);
            
            if (success) {
                request.setAttribute("success", "Thêm điện thoại thành công! Mã: " + maDT);
                // Redirect về trang danh sách với thông tin NCC được chọn
                response.sendRedirect(request.getContextPath() + "/DanhSachDienThoaiNCCServlet?maNCC=" + maNCC);
            } else {
                request.setAttribute("error", "Có lỗi xảy ra khi thêm điện thoại vào database!");
                request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi xử lý form: " + e.getMessage());
            request.getRequestDispatcher("/view/dienThoaiForm.jsp").forward(request, response);
        }
    }
}
