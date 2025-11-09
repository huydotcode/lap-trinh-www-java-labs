package iuh.fit.se.repository;

import iuh.fit.se.entity.Phone;
import iuh.fit.se.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    
    // Tìm tất cả điện thoại theo nhà cung cấp
    List<Phone> findBySupplier(Supplier supplier);
    
    // Tìm tất cả điện thoại theo ID nhà cung cấp
    List<Phone> findBySupplierId(Integer supplierId);
    
    // Tìm kiếm điện thoại theo tên (chứa chuỗi)
    List<Phone> findByNameContainingIgnoreCase(String name);
    
    // Tìm kiếm điện thoại theo năm sản xuất
    List<Phone> findByYear(int year);
    
    // Tìm kiếm tổng hợp: tìm theo tên, năm, hoặc cấu hình
    @Query("SELECT p FROM Phone p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "CAST(p.year AS string) LIKE CONCAT('%', :keyword, '%') OR " +
           "LOWER(p.properties) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Phone> searchPhones(@Param("keyword") String keyword);
    
    // Tìm kiếm điện thoại theo nhà cung cấp và keyword
    @Query("SELECT p FROM Phone p WHERE p.supplier.id = :supplierId AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "CAST(p.year AS string) LIKE CONCAT('%', :keyword, '%') OR " +
           "LOWER(p.properties) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Phone> searchPhonesBySupplier(@Param("supplierId") Integer supplierId, 
                                       @Param("keyword") String keyword);
}
