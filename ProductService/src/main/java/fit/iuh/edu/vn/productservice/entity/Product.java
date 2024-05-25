package fit.iuh.edu.vn.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="products")
public class Product {
    @Id
    @Column(name="productId")
    private String id;
    private String name;
    private double price;
    private  int quantity;
}
