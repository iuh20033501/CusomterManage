package fit.iuh.edu.vn.purchaseservice.entity;

import fit.iuh.edu.vn.purchaseservice.service.ProductClientService;
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
        ProductClientService productClientService = new ProductClientService(restTemplate);
        for (String productId : productIds) {
            Double productPrice = productClientService.getProductPrice(productId);
            if (productPrice != null) {
                totalPrice += productPrice;
            }
        }
        return totalPrice;
    }

}
