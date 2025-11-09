package iuh.fit.se.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhoneResponse {
    private Integer id;
    private String name;
    private Integer year;
    private String properties;
    private String image;
    private SupplierResponse supplier;
}