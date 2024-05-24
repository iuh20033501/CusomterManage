package fit.iuh.edu.vn.purchaseservice.entity;

import fit.iuh.edu.vn.purchaseservice.controller.PurchaseController;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="purchases")
public class Purchase {
    @Id
    @Column(name ="purchaseId")
    private String id;
    @ElementCollection
    private List<String> productIds;
    private LocalDate purchaseDate;
    private int status;
    private  double totalprice;
    public Double calculateTotalPrice(RestTemplate restTemplate) {
        double totalPrice = 0.0;
        PurchaseController purchaseController = new PurchaseController(restTemplate);
        for (String productId : productIds) {
            Double productPrice = purchaseController.getProductPrice(productId);
            if (productPrice != null) {
                totalPrice += productPrice;
            }
        }
        return totalPrice;
    }

}
