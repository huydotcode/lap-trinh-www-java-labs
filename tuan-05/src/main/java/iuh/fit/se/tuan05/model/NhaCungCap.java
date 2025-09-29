package iuh.fit.se.tuan05.model;

/**
 * Model class cho bảng NHACUNGCAP
 * 
 * @author Student
 */
public class NhaCungCap {
    private String maNCC;
    private String tenNHacc;
    private String diaChi;
    private String soDienThoai;
    
    // Default constructor
    public NhaCungCap() {
    }
    
    // Constructor with all parameters
    public NhaCungCap(String maNCC, String tenNHacc, String diaChi, String soDienThoai) {
        this.maNCC = maNCC;
        this.tenNHacc = tenNHacc;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }
    
    // Getters and Setters
    public String getMaNCC() {
        return maNCC;
    }
    
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
    
    public String getTenNHacc() {
        return tenNHacc;
    }
    
    public void setTenNHacc(String tenNHacc) {
        this.tenNHacc = tenNHacc;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    @Override
    public String toString() {
        return "NhaCungCap{" +
                "maNCC='" + maNCC + '\'' +
                ", tenNHacc='" + tenNHacc + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }
}
