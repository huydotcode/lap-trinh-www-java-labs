package iuh.fit.se.tuan05.servlet;

import iuh.fit.se.tuan05.dao.DienThoaiDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet để xử lý quản lý và xóa điện thoại
 * 
 * @author Student
 */
@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    
    private DienThoaiDAO dienThoaiDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        dienThoaiDAO = new DienThoaiDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Thiết lập currentPage cho navbar
            request.setAttribute("currentPage", "manage");
            request.setAttribute("pageTitle", "Quản Lý Sản Phẩm");
            
            // Lấy danh sách tất cả điện thoại
            var allDienThoai = dienThoaiDAO.getAllDienThoai();
            request.setAttribute("allDienThoai", allDienThoai);
            
            // Forward đến JSP
            request.getRequestDispatcher("/view/quanLyForm.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("/view/quanLyForm.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Thiết lập currentPage cho navbar
            request.setAttribute("currentPage", "manage");
            request.setAttribute("pageTitle", "Quản Lý Sản Phẩm");
            
            // Lấy mã điện thoại cần xóa
            String maDT = request.getParameter("maDT");
            
            if (maDT != null && !maDT.trim().isEmpty()) {
                // Thực hiện xóa điện thoại
                boolean success = dienThoaiDAO.deleteDienThoai(maDT.trim());
                
                if (success) {
                    request.setAttribute("success", "Xóa điện thoại thành công! Mã: " + maDT);
                } else {
                    request.setAttribute("error", "Không thể xóa điện thoại với mã: " + maDT + ". Có thể mã không tồn tại!");
                }
            } else {
                request.setAttribute("error", "Vui lòng chọn điện thoại cần xóa!");
            }
            
            // Lấy lại danh sách sau khi xóa
            var allDienThoai = dienThoaiDAO.getAllDienThoai();
            request.setAttribute("allDienThoai", allDienThoai);
            
            // Forward đến JSP
            request.getRequestDispatcher("/view/quanLyForm.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi xử lý yêu cầu: " + e.getMessage());
            
            try {
                // Lấy lại danh sách trong trường hợp có lỗi
                var allDienThoai = dienThoaiDAO.getAllDienThoai();
                request.setAttribute("allDienThoai", allDienThoai);
            } catch (Exception ex) {
                System.err.println("Lỗi khi load lại danh sách: " + ex.getMessage());
            }
            
            // Forward đến JSP
            request.getRequestDispatcher("/view/quanLyForm.jsp").forward(request, response);
        }
    }
}
