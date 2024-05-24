package fit.iuh.edu.vn.purchaseservice.service;

import fit.iuh.edu.vn.purchaseservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductClientService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Product> getProductById(String productId) {
        try {
            Product product = restTemplate.getForObject("http://localhost:8082/api/products/findById/" + productId, Product.class);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
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

    public Double getProductPrice(String productId) {
        String getProductUrl = "http://localhost:8082/api/product/" + productId + "/price";
        ResponseEntity<Double> response = restTemplate.getForEntity(getProductUrl, Double.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.err.println("Lỗi khi lấy giá cho ID sản phẩm: " + productId);
            return null;
        }
    }

}
