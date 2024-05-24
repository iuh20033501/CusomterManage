package fit.iuh.edu.vn.purchaseservice;

import fit.iuh.edu.vn.purchaseservice.entity.Purchase;
import fit.iuh.edu.vn.purchaseservice.repository.PurchaseRepository;
import fit.iuh.edu.vn.purchaseservice.service.ProductClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class PurchaseServiceApplication {
    private final PurchaseRepository purchaseRepository;
    private final ProductClientService productClientService;

    @Autowired
    public PurchaseServiceApplication(PurchaseRepository purchaseRepository, ProductClientService productClientService) {
        this.purchaseRepository = purchaseRepository;
        this.productClientService = productClientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PurchaseServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Purchase purchase = new Purchase();
            purchase.setProductIds(Arrays.asList("LK02","LK01"));
            purchase.setId("P12");
            purchase.setPurchaseDate(LocalDate.now());
            purchase.setStatus(1);
            double totalPrice = 0.0;
            for (String productId : purchase.getProductIds()) {
                Double productPrice = productClientService.getProductPrice(productId);
                if (productPrice != null) {
                    totalPrice += productPrice;
                }
            }
            purchase.setTotalprice(totalPrice);
            purchaseRepository.save(purchase);
            System.out.println("Purchase created: " + purchase);


        };
    }
}
