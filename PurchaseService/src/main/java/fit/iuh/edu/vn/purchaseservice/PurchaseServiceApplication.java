package fit.iuh.edu.vn.purchaseservice;

import fit.iuh.edu.vn.purchaseservice.controller.PurchaseController;
import fit.iuh.edu.vn.purchaseservice.entity.Product;
import fit.iuh.edu.vn.purchaseservice.entity.Purchase;
import fit.iuh.edu.vn.purchaseservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PurchaseServiceApplication {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseController purchaseController;


    @Autowired
    public PurchaseServiceApplication(PurchaseRepository purchaseRepository, PurchaseController purchaseController) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseController = purchaseController;

    }

    public static void main(String[] args) {
        SpringApplication.run(PurchaseServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            Purchase purchase = new Purchase();
//            purchase.setProductIds(Arrays.asList("LK02","LK01"));
//            purchase.setId("P14");
//            purchase.setPurchaseDate(LocalDate.now());
//            purchase.setStatus(1);
//            double totalPrice = 0.0;
//            for (String productId : purchase.getProductIds()) {
//                Double productPrice = productClientService.getProductPrice(productId);
//                if (productPrice != null) {
//                    totalPrice += productPrice;
//                }
//            }
//            purchase.setTotalprice(totalPrice);
//            purchaseController.createPurchase((List<String>) purchase);
//            System.out.println("Purchase created: " + purchase);
//                Product product = purchaseController.getProductById("LK01");
//                System.out.println(product);
            List<Product> lsProducts = purchaseController.getAllProduct();
            lsProducts.forEach(x -> System.out.println(x));
        };
    }
}
