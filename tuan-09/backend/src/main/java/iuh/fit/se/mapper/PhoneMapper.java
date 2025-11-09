package iuh.fit.se.mapper;

import iuh.fit.se.dto.request.PhoneRequest;
import iuh.fit.se.dto.response.PhoneResponse;
import iuh.fit.se.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

    @Autowired
    private SupplierMapper supplierMapper;

    public PhoneResponse toResponse(Phone phone) {
        if (phone == null) return null;
        PhoneResponse response = new PhoneResponse();
        response.setId(phone.getId());
        response.setName(phone.getName());
        response.setYear(phone.getYear());
        response.setProperties(phone.getProperties());
        response.setImage(phone.getImage());
        if (phone.getSupplier() != null) {
            response.setSupplier(supplierMapper.toResponse(phone.getSupplier()));
        }
        return response;
    }

    public Phone toEntity(PhoneRequest request) {
        if (request == null) return null;
        Phone phone = new Phone();
        phone.setName(request.getName());
        phone.setYear(request.getYear());
        phone.setProperties(request.getProperties());
        phone.setImage(request.getImage());
        // supplier sẽ set sau trong service
        return phone;
    }
    
    public void updateEntity(PhoneRequest request, Phone phone) {
        if (request == null || phone == null) return;
        
        if (request.getName() != null) {
            phone.setName(request.getName());
        }
        if (request.getYear() != null) {
            phone.setYear(request.getYear());
        }
        if (request.getProperties() != null) {
            phone.setProperties(request.getProperties());
        }
        if (request.getImage() != null) {
            phone.setImage(request.getImage());
        }
        // supplier sẽ update trong service nếu cần
    }
}