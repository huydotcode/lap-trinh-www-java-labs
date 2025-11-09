package iuh.fit.se.mapper;

import iuh.fit.se.dto.request.SupplierRequest;
import iuh.fit.se.dto.response.SupplierResponse;
import iuh.fit.se.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public SupplierResponse toResponse(Supplier supplier) {
        if (supplier == null) return null;
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setAddress(supplier.getAddress());
        response.setPhoneNumber(supplier.getPhoneNumber());
        return response;
    }

    public Supplier toEntity(SupplierRequest request) {
        if (request == null) return null;
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setPhoneNumber(request.getPhoneNumber());
        return supplier;
    }

    public void updateEntity(SupplierRequest request, Supplier supplier) {
        if (request == null || supplier == null) return;
        if (request.getName() != null) {
            supplier.setName(request.getName());
        }
        if (request.getAddress() != null) {
            supplier.setAddress(request.getAddress());
        }
        if (request.getPhoneNumber() != null) {
            supplier.setPhoneNumber(request.getPhoneNumber());
        }
    }
}