package iuh.fit.se.service;

import iuh.fit.se.dto.request.PhoneRequest;
import iuh.fit.se.dto.response.PhoneResponse;

import java.util.List;

public interface PhoneService extends BaseService<Integer, PhoneRequest, PhoneResponse> {
    // Xem danh sách sản phẩm theo nhà cung cấp
    List<PhoneResponse> findBySupplierId(Integer supplierId);
    
    // Tìm kiếm sản phẩm
    List<PhoneResponse> searchPhones(String keyword);
}
