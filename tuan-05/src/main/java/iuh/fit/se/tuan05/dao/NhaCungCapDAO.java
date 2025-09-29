package iuh.fit.se.tuan05.dao;

import iuh.fit.se.tuan05.model.NhaCungCap;
import iuh.fit.se.tuan05.util.DatabaseUtil;
import java.sql.*;
import java.util.*;

/**
 * DAO class để thao tác với bảng NHACUNGCAP
 * 
 * @author Student
 */
public class NhaCungCapDAO {
    
    /**
     * Lấy tất cả nhà cung cấp
     * @return List của NhaCungCap
     */
    public List<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> nhaCungCapList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM NHACUNGCAP ORDER BY TENNHACC";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MANCC"));
                ncc.setTenNHacc(rs.getString("TENNHACC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSoDienThoai(rs.getString("SODIENTHOAI"));
                nhaCungCapList.add(ncc);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách nhà cung cấp: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, stmt, conn);
        }
        
        return nhaCungCapList;
    }
    
    /**
     * Tìm kiếm nhà cung cấp theo tiêu chí
     * @param searchTerm từ khóa tìm kiếm
     * @return List của NhaCungCap
     */
    public List<NhaCungCap> searchNhaCungCap(String searchTerm) {
        List<NhaCungCap> nhaCungCapList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM NHACUNGCAP WHERE " +
                        "MANCC LIKE ? OR TENNHACC LIKE ? OR DIACHI LIKE ? OR SODIENTHOAI LIKE ? " +
                        "ORDER BY TENNHACC";
            pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MANCC"));
                ncc.setTenNHacc(rs.getString("TENNHACC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSoDienThoai(rs.getString("SODIENTHOAI"));
                nhaCungCapList.add(ncc);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm nhà cung cấp: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return nhaCungCapList;
    }
    
    /**
     * Lấy nhà cung cấp theo mã
     * @param maNCC mã nhà cung cấp
     * @return NhaCungCap object
     */
    public NhaCungCap getNhaCungCapByMaNCC(String maNCC) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NhaCungCap ncc = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM NHACUNGCAP WHERE MANCC = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNCC);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MANCC"));
                ncc.setTenNHacc(rs.getString("TENNHACC"));
                ncc.setDiaChi(rs.getString("DIACHI"));
                ncc.setSoDienThoai(rs.getString("SODIENTHOAI"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy nhà cung cấp theo mã: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return ncc;
    }
}
