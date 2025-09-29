package iuh.fit.se.tuan05.servlet;

import iuh.fit.se.tuan05.dao.DienThoaiDAO;
import iuh.fit.se.tuan05.dao.NhaCungCapDAO;
import iuh.fit.se.tuan05.model.DienThoai;
import iuh.fit.se.tuan05.model.NhaCungCap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet để xử lý danh sách điện thoại theo nhà cung cấp và tìm kiếm NCC
 * 
 * @author Student
 */
@WebServlet("/DanhSachDienThoaiNCCServlet")
public class DanhSachDienThoaiNCCServlet extends HttpServlet {
    
    private DienThoaiDAO dienThoaiDAO;
    private NhaCungCapDAO nhaCungCapDAO;
    
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
            request.setAttribute("currentPage", "list");
            request.setAttribute("pageTitle", "Danh Sách Điện Thoại");
            
            // Lấy tham số search từ request
            String searchTerm = request.getParameter("search");
            String maNCC = request.getParameter("maNCC");
            
            List<DienThoai> allDienThoai = null;
            List<NhaCungCap> searchResults = null;
            
            // Nếu có từ khóa tìm kiếm
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                searchResults = nhaCungCapDAO.searchNhaCungCap(searchTerm.trim());
                request.setAttribute("searchTerm", searchTerm.trim());
                request.setAttribute("searchResults", searchResults);
                request.setAttribute("message", "Tìm thấy " + searchResults.size() + " nhà cung cấp phù hợp!");
            }
            // Nếu chọn mã NCC cụ thể
            else if (maNCC != null && !maNCC.trim().isEmpty()) {
                List<DienThoai> dienThoaiByNCC = dienThoaiDAO.getDienThoaiByNCC(maNCC.trim());
                request.setAttribute("selectedNCC", maNCC.trim());
                request.setAttribute("dienThoaiByNCC", dienThoaiByNCC);
                
                // Lấy thông tin NCC được chọn
                NhaCungCap selectedNhaCungCap = nhaCungCapDAO.getNhaCungCapByMaNCC(maNCC.trim());
                request.setAttribute("selectedNhaCungCap", selectedNhaCungCap);
                
                request.setAttribute("message", "Hiển thị " + dienThoaiByNCC.size() + " điện thoại của nhà cung cấp " + 
                    (selectedNhaCungCap != null ? selectedNhaCungCap.getTenNHacc() : ""));
            }
            // Lấy tất cả điện thoại và group theo NCC
            else {
                allDienThoai = dienThoaiDAO.getAllDienThoai();
                
                // Group điện thoại theo NHACC
                Map<String, List<DienThoai>> dienThoaiGroupedByNCC = new HashMap<>();
                for (DienThoai dt : allDienThoai) {
                    String nccName = dt.getNhaCungCap().getTenNHacc();
                    dienThoaiGroupedByNCC.computeIfAbsent(nccName, k -> new ArrayList<>()).add(dt);
                }
                
                request.setAttribute("dienThoaiGroupedByNCC", dienThoaiGroupedByNCC);
                request.setAttribute("message", "Danh sách tất cả điện thoại được nhóm theo nhà cung cấp");
            }
            
            // Lấy danh sách tất cả NCC để hiển thị dropdown
            List<NhaCungCap> allNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
            request.setAttribute("allNhaCungCap", allNhaCungCap);
            
            // Forward đến JSP
            request.getRequestDispatcher("/view/danhSachDienThoaiNCC.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("/view/danhSachDienThoaiNCC.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Xử lý form submit (search hoặc filter)
        doGet(request, response);
    }
}
