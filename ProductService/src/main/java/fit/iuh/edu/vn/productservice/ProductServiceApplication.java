package fit.iuh.edu.vn.productservice;

import fit.iuh.edu.vn.productservice.controller.ProductController;
import fit.iuh.edu.vn.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProductServiceApplication {
    @Autowired
    ProductController productController;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product p1 = new Product("LK02","Tai nghe dareU",1000000);
//                productController.createProduct(p1);
                List<Product> listP = new ArrayList<>();
                listP = productController.getAllProduct();
                System.out.println(listP);
            }
        };
    }
}
