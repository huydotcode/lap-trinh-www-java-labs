package iuh.fit.se.service.impl;

import iuh.fit.se.dto.request.PhoneRequest;
import iuh.fit.se.dto.response.PhoneResponse;
import iuh.fit.se.entity.Phone;
import iuh.fit.se.entity.Supplier;
import iuh.fit.se.exception.ResourceNotFoundException;
import iuh.fit.se.mapper.PhoneMapper;
import iuh.fit.se.repository.PhoneRepository;
import iuh.fit.se.repository.SupplierRepository;
import iuh.fit.se.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PhoneResponse> findAll() {
        return phoneRepository
                .findAll()
                .stream()
                .map(phoneMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PhoneResponse> findById(Integer integer) {
        return phoneRepository
                .findById(integer)
                .map(phoneMapper::toResponse);
    }

    @Override
    public PhoneResponse create(PhoneRequest phoneRequest) {

        Phone phone = phoneMapper.toEntity(phoneRequest);
        Supplier supplier = supplierRepository.findById(phoneRequest.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + phoneRequest.getSupplierId()));
        phone.setSupplier(supplier);

        Phone savedPhone = phoneRepository.save(phone);
        return phoneMapper.toResponse(savedPhone);
    }

    @Override
    public PhoneResponse update(Integer integer, PhoneRequest phoneRequest) {
        Phone phone = phoneRepository.findById(integer)
                .orElseThrow(() -> new ResourceNotFoundException("Phone not found with id: " + integer));

        // Sử dụng mapper để update
        phoneMapper.updateEntity(phoneRequest, phone);

        // Update supplier nếu có thay đổi
        if (phoneRequest.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(phoneRequest.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + phoneRequest.getSupplierId()));
            phone.setSupplier(supplier);
        }

        Phone updatedPhone = phoneRepository.save(phone);
        return phoneMapper.toResponse(updatedPhone);
    }

    @Override
    public void delete(Integer integer) {
        if (!phoneRepository.existsById(integer)) {
            throw new ResourceNotFoundException("Phone not found with id: " + integer);
        }
        phoneRepository.deleteById(integer);
    }
    
    @Override
    public List<PhoneResponse> findBySupplierId(Integer supplierId) {
        // Kiểm tra supplier có tồn tại không
        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + supplierId);
        }
        
        List<Phone> phones = phoneRepository.findBySupplierId(supplierId);
        return phones.stream()
                .map(phoneMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PhoneResponse> searchPhones(String keyword) {
        List<Phone> phones = phoneRepository.searchPhones(keyword);
        return phones.stream()
                .map(phoneMapper::toResponse)
                .collect(Collectors.toList());
    }
}
