package fit.iuh.edu.vn.purchaseservice.controller;

import fit.iuh.edu.vn.purchaseservice.entity.Product;
import fit.iuh.edu.vn.purchaseservice.entity.Purchase;
import fit.iuh.edu.vn.purchaseservice.repository.PurchaseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private RetryTemplate retryTemplate;

    public PurchaseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getAll")
    public List<Purchase> getAllPurchase() {
        List<Purchase> allPurchase = purchaseRepository.findAll();
        return allPurchase.stream()
                .filter(purchase -> purchase.getStatus() == 1)
                .collect(Collectors.toList());
    }
    //CircuitBreaker
    @GetMapping("/getAllProduct")
    @CircuitBreaker(name = "getAllProduct", fallbackMethod = "getAllProductFallback")
    public List<Product> getAllProduct() {
        String getAllProductUrl = "http://localhost:8082/api/product/getAll";

        try {
            return retryTemplate.execute(new RetryCallback<List<Product>, RuntimeException>() {
                @Override
                public List<Product> doWithRetry(RetryContext retryContext) throws RuntimeException {
                    System.out.println("Số lần retry: " + (retryContext.getRetryCount() + 1));
                    ResponseEntity<List<Product>> response = restTemplate.exchange(
                            getAllProductUrl,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Product>>() {});

                    if (response.getStatusCode() == HttpStatus.OK) {
                        return response.getBody();
                    } else {
                        throw new RuntimeException("lỗi fetching");
                    }
                }
            });
        } catch (RuntimeException e) {
            System.err.println("Lỗi" + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Phương thức fallback
    public List<Product> getAllProductFallback(Exception e) {
        return Collections.emptyList();
    }
    @GetMapping("/findById/{id}")
    public Purchase getPurchaseById(@PathVariable String id) {
        return purchaseRepository.findById(id).orElse(null);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody List<String> productIds) {
        if (productIds.isEmpty()) {
            return ResponseEntity.badRequest().body("Product IDs cannot be empty");
        }
        Optional<List<Product>> productsOptional = getProductsByIds(productIds);
        if (productsOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Products not found");
        }
        Purchase purchase = new Purchase();
        purchase.setProductIds(productIds);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setStatus(1);
        for (String productId : productIds) {
            ResponseEntity<String> response = reduceProductQuantity(productId);
            if (!response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.badRequest().body("Failed to reduce quantity for product ID: " + productId);
            }
        }
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

public Product getProductById(String productId) {
    String getProductUrl = "http://localhost:8082/api/product/findById/{id}" + productId ;
    ResponseEntity<Product> response = restTemplate.getForEntity(getProductUrl, Product.class);
    if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
    } else {
        System.err.println("Lỗi khi lấy giá cho ID sản phẩm: " + productId);
        return null;
    }
}


    public Optional<List<Product>> getProductsByIds(List<String> productIds) {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://localhost:8082/api/products/findByIds");
            for (String productId : productIds) {
                urlBuilder.append("productId=").append(productId).append("&");
            }
            // Remove the last "&" character
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);

            Product[] products = restTemplate.getForObject(urlBuilder.toString(), Product[].class);
            return Optional.ofNullable(Arrays.asList(products));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @GetMapping("/getProdcutPriceById")
    public Double getProductPrice( @RequestBody  String productId) {
        String getProductUrl = "http://localhost:8082/api/product/" + productId + "/price";
        ResponseEntity<Double> response = restTemplate.getForEntity(getProductUrl, Double.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.err.println("Lỗi khi lấy giá cho ID sản phẩm: " + productId);
            return null;
        }
    }
    public Integer getProductQuantity(String productId) {
        String getProductUrl = "http://localhost:8082/api/product/" + productId + "/quantity";
        ResponseEntity<Integer> response = restTemplate.getForEntity(getProductUrl, Integer.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.err.println("Lỗi khi lấy số lượng cho ID sản phẩm: " + productId);
            return null;
        }
    }
    public ResponseEntity<String> reduceProductQuantity(String productId) {
        String url = "http://localhost:8082/api/product/reduce/" + productId;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response;
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to reduce product quantity");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reducing product quantity: " + e.getMessage());
        }
    }

}
