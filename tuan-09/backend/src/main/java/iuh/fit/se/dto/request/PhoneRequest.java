package iuh.fit.se.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhoneRequest {
    @NotBlank(message = "Tên điện thoại không được để trống")
    private String name;

    @NotNull(message = "Năm sản xuất không được để trống")
    @Min(value = 1900, message = "Năm sản xuất phải từ 1900")
    @Max(value = 9999, message = "Năm sản xuất phải là 4 chữ số")
    private Integer year;

    @NotBlank(message = "Cấu hình không được để trống")
    @Size(max = 255, message = "Cấu hình không được quá 255 ký tự")
    private String properties;

    private String image;

    @NotNull(message = "Nhà cung cấp không được để trống")
    private Integer supplierId;
}