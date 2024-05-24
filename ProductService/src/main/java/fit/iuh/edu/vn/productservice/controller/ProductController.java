package fit.iuh.edu.vn.productservice.controller;

import fit.iuh.edu.vn.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fit.iuh.edu.vn.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getAll")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}/price")
    public Double getProductPrice(@PathVariable String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return product.getPrice();
        } else {
            return null;
        }
    }


    @GetMapping("/findById/{id}")
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    @PostMapping("/create")
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product product1 = productRepository.findById(id).orElse(null);
        if (product1 != null) {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            return productRepository.save(product1);
        }
        return null;
    }

    @PutMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable String id) {
        Product product1 = productRepository.findById(id).orElse(null);
        if(product1 != null) {
            productRepository.deleteById(id);
        }
        return null;
    }

}
