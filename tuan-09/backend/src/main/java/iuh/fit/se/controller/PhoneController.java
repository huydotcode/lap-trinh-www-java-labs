package iuh.fit.se.controller;

import iuh.fit.se.dto.request.PhoneRequest;
import iuh.fit.se.dto.response.PhoneResponse;
import iuh.fit.se.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phones")
@CrossOrigin(origins = "*")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public ResponseEntity<List<PhoneResponse>> getAllPhones() {
        List<PhoneResponse> phones = phoneService.findAll();
        return ResponseEntity.ok(phones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneResponse> getPhoneById(@PathVariable Integer id) {
        Optional<PhoneResponse> phone = phoneService.findById(id);
        return phone
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PhoneResponse> createPhone(@Valid @RequestBody PhoneRequest phoneRequest) {
        PhoneResponse response = phoneService.create(phoneRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneResponse> updatePhone(@PathVariable Integer id, @Valid @RequestBody PhoneRequest phoneRequest) {
        PhoneResponse response = phoneService.update(id, phoneRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Integer id) {
        phoneService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    // Xem danh sách sản phẩm theo nhà cung cấp
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PhoneResponse>> getPhonesBySupplier(@PathVariable Integer supplierId) {
        List<PhoneResponse> phones = phoneService.findBySupplierId(supplierId);
        return ResponseEntity.ok(phones);
    }
    
    // Tìm kiếm sản phẩm
    @GetMapping("/search")
    public ResponseEntity<List<PhoneResponse>> searchPhones(@RequestParam String keyword) {
        List<PhoneResponse> phones = phoneService.searchPhones(keyword);
        return ResponseEntity.ok(phones);
    }
}
