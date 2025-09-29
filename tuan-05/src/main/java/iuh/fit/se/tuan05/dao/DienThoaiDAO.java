package iuh.fit.se.tuan05.dao;

import iuh.fit.se.tuan05.model.DienThoai;
import iuh.fit.se.tuan05.model.NhaCungCap;
import iuh.fit.se.tuan05.util.DatabaseUtil;
import java.sql.*;
import java.util.*;

/**
 * DAO class để thao tác với bảng DIENTHOAI
 * 
 * @author Student
 */
public class DienThoaiDAO {
    
    /**
     * Lấy tất cả điện thoại với thông tin nhà cung cấp
     * @return List của DienThoai
     */
    public List<DienThoai> getAllDienThoai() {
        List<DienThoai> dienThoaiList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT dt.*, ncc.TENNHACC, ncc.DIACHI, ncc.SODIENTHOAI " +
                        "FROM DIENTHOAI dt " +
                        "JOIN NHACUNGCAP ncc ON dt.MANCC = ncc.MANCC " +
                        "ORDER BY ncc.TENNHACC, dt.TENDT";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                DienThoai dt = new DienThoai();
                dt.setMaDT(rs.getString("MADT"));
                dt.setTenDT(rs.getString("TENDT"));
                dt.setNamSanXuat(rs.getInt("NAMSANXUAT"));
                dt.setCauHinh(rs.getString("CAUHINH"));
                dt.setMaNCC(rs.getString("MANCC"));
                dt.setHinhAnh(rs.getString("HINHANH"));
                
                // Tạo NhaCungCap object
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MANCC"));
                ncc.setTenNHacc(rs.getString("TENNHACC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSoDienThoai(rs.getString("SODIENTHOAI"));
                
                dt.setNhaCungCap(ncc);
                dienThoaiList.add(dt);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách điện thoại: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, stmt, conn);
        }
        
        return dienThoaiList;
    }
    
    /**
     * Lấy điện thoại theo nhà cung cấp
     * @param maNCC mã nhà cung cấp
     * @return List của DienThoai
     */
    public List<DienThoai> getDienThoaiByNCC(String maNCC) {
        List<DienThoai> dienThoaiList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT dt.*, ncc.TENNHACC, ncc.DIACHI, ncc.SODIENTHOAI " +
                        "FROM DIENTHOAI dt " +
                        "JOIN NHACUNGCAP ncc ON dt.MANCC = ncc.MANCC " +
                        "WHERE dt.MANCC = ? " +
                        "ORDER BY dt.TENDT";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNCC);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                DienThoai dt = new DienThoai();
                dt.setMaDT(rs.getString("MADT"));
                dt.setTenDT(rs.getString("TENDT"));
                dt.setNamSanXuat(rs.getInt("NAMSANXUAT"));
                dt.setCauHinh(rs.getString("CAUHINH"));
                dt.setMaNCC(rs.getString("MANCC"));
                dt.setHinhAnh(rs.getString("HINHANH"));
                
                // Tạo NhaCungCap object
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MANCC"));
                ncc.setTenNHacc(rs.getString("TENNHACC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSoDienThoai(rs.getString("SODIENTHOAI"));
                
                dt.setNhaCungCap(ncc);
                dienThoaiList.add(dt);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy điện thoại theo NCC: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return dienThoaiList;
    }
    
    /**
     * Thêm điện thoại mới
     * @param dienThoai DienThoai object
     * @return true nếu thêm successful
     */
    public boolean addDienThoai(DienThoai dienThoai) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO DIENTHOAI (MADT, TENDT, NAMSANXUAT, CAUHINH, MANCC, HINHANH) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dienThoai.getMaDT());
            pstmt.setString(2, dienThoai.getTenDT());
            pstmt.setInt(3, dienThoai.getNamSanXuat());
            pstmt.setString(4, dienThoai.getCauHinh());
            pstmt.setString(5, dienThoai.getMaNCC());
            pstmt.setString(6, dienThoai.getHinhAnh());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm điện thoại: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
    }
    
    /**
     * Xóa điện thoại theo mã
     * @param maDT mã điện thoại
     * @return true nếu xóa successful
     */
    public boolean deleteDienThoai(String maDT) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "DELETE FROM DIENTHOAI WHERE MADT = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maDT);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa điện thoại: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
    }
    
    /**
     * Kiểm tra mã điện thoại đã tồn tại chưa
     * @param maDT mã điện thoại
     * @return true nếu đã tồn tại
     */
    public boolean existsDienThoai(String maDT) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM DIENTHOAI WHERE MADT = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maDT);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra tồn tại điện thoại: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
    }
}
