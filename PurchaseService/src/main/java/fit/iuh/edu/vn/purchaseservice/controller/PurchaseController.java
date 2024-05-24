package fit.iuh.edu.vn.purchaseservice.controller;

import fit.iuh.edu.vn.purchaseservice.entity.Product;
import fit.iuh.edu.vn.purchaseservice.entity.Purchase;
import fit.iuh.edu.vn.purchaseservice.repository.PurchaseRepository;
import fit.iuh.edu.vn.purchaseservice.service.ProductClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private  ProductClientService productClientService;

    @GetMapping("/getAll")
    public List<Purchase> getAllPurchase() {
        List<Purchase> allPurchase = purchaseRepository.findAll();
        return allPurchase.stream()
                .filter(purchase -> purchase.getStatus() == 1)
                .collect(Collectors.toList());
    }
    @GetMapping("/findById/{id}")
    public Optional<Purchase> getPurchaseById(String id) {
        return purchaseRepository.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody List<String> productIds) {
        if (productIds.isEmpty()) {
            return ResponseEntity.badRequest().body("Product IDs cannot be empty");
        }

        Optional<List<Product>> productsOptional = productClientService.getProductsByIds(productIds);
        if (productsOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Products not found");
        }

        // Tạo đối tượng Purchase
        Purchase purchase = new Purchase();
        purchase.setProductIds(productIds);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setStatus(1);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPurchase);
    }


    @PutMapping("/delete/{id}")
    public Purchase deletePurchase(@PathVariable String id) {
        Purchase purchase =purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            purchase.setStatus(0);
            return  purchaseRepository.save(purchase);
        }
        return null;
    }
}
