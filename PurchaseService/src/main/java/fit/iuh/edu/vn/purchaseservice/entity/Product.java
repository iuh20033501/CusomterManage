package fit.iuh.edu.vn.purchaseservice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
