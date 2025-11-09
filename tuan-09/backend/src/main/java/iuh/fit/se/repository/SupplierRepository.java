package iuh.fit.se.repository;

import iuh.fit.se.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
    // Tìm kiếm theo tên (chứa chuỗi)
    List<Supplier> findByNameContainingIgnoreCase(String name);
    
    // Tìm kiếm theo địa chỉ (chứa chuỗi)
    List<Supplier> findByAddressContainingIgnoreCase(String address);
    
    // Tìm kiếm theo số điện thoại (chứa chuỗi)
    List<Supplier> findByPhoneNumberContaining(String phoneNumber);
    
    // Tìm kiếm tổng hợp: tìm theo bất kỳ field nào (MANCC, TENNHACC, DIACHI, SODIENTHOAI)
    @Query("SELECT s FROM Supplier s WHERE " +
           "CAST(s.id AS string) LIKE CONCAT('%', :keyword, '%') OR " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "s.phoneNumber LIKE CONCAT('%', :keyword, '%')")
    List<Supplier> searchSuppliers(@Param("keyword") String keyword);
}
