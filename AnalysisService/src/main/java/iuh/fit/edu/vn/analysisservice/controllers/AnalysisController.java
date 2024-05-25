/*
 * @ (#) $NAME.java         5/25/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package iuh.fit.edu.vn.analysisservice.controllers;

import iuh.fit.edu.vn.analysisservice.models.Analysis;
import iuh.fit.edu.vn.analysisservice.models.Payment;
import iuh.fit.edu.vn.analysisservice.models.Product;
import iuh.fit.edu.vn.analysisservice.services.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 5/25/2024
 */
@RestController
@RequestMapping("/api/v1/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = analysisService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = analysisService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Analysis>> getAllAnalysis() {
        List<Analysis> analyses = analysisService.getAllAnalyses();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = analysisService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment newPayment = analysisService.createPayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<Analysis> createAnalysis(@RequestBody Analysis analysis) {
        Analysis newAnalysis = analysisService.createAnalysis(analysis);
        return new ResponseEntity<>(newAnalysis, HttpStatus.CREATED);
    }

    @GetMapping("/averageTotalSales")
    public ResponseEntity<Double> getAverageTotalSales() {
        double averageTotalSales = analysisService.calculateAverageTotalSales();
        return new ResponseEntity<>(averageTotalSales, HttpStatus.OK);
    }

    @GetMapping("/averageTotalRevenue")
    public ResponseEntity<BigDecimal> getAverageTotalRevenue() {
        BigDecimal averageTotalRevenue = analysisService.calculateAverageTotalRevenue();
        return new ResponseEntity<>(averageTotalRevenue, HttpStatus.OK);
    }

}
