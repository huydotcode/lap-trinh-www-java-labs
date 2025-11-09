package iuh.fit.se.service.impl;

import iuh.fit.se.dto.request.SupplierRequest;
import iuh.fit.se.dto.response.SupplierResponse;
import iuh.fit.se.entity.Supplier;
import iuh.fit.se.exception.ResourceNotFoundException;
import iuh.fit.se.mapper.SupplierMapper;
import iuh.fit.se.repository.SupplierRepository;
import iuh.fit.se.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SupplierResponse> findById(Integer id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toResponse);
    }

    @Override
    public SupplierResponse create(SupplierRequest request) {
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toResponse(savedSupplier);
    }

    @Override
    public SupplierResponse update(Integer id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        supplierMapper.updateEntity(request, supplier);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toResponse(updatedSupplier);
    }

    @Override
    public void delete(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }
    
    @Override
    public List<SupplierResponse> searchSuppliers(String keyword) {
        List<Supplier> suppliers = supplierRepository.searchSuppliers(keyword);
        return suppliers.stream()
                .map(supplierMapper::toResponse)
                .collect(Collectors.toList());
    }
}