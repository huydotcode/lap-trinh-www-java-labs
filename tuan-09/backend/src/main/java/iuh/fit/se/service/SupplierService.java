package iuh.fit.se.service;

import iuh.fit.se.dto.request.SupplierRequest;
import iuh.fit.se.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService extends BaseService<Integer, SupplierRequest, SupplierResponse> {
    // Tìm kiếm thông tin NCC theo MANCC hoặc TENNHACC hoặc DIACHI hoặc SODIENTHOAI
    List<SupplierResponse> searchSuppliers(String keyword);
}
