package iuh.fit.se.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SupplierResponse {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
}