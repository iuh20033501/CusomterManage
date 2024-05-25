package fit.iuh.edu.vn.deliveryservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String idCustomer;
    private String customerName;
    private String phoneNumber;
    private String email;

}
