package fit.iuh.edu.vn.deliveryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="delivery")
public class Delivery {
    private String id;
    private String location;
    private Customer customer;

}
