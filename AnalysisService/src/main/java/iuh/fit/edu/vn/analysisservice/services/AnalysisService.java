/*
 * @ (#) $NAME.java         5/25/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package iuh.fit.edu.vn.analysisservice.services;

import iuh.fit.edu.vn.analysisservice.models.Analysis;
import iuh.fit.edu.vn.analysisservice.models.Payment;
import iuh.fit.edu.vn.analysisservice.models.Product;
import iuh.fit.edu.vn.analysisservice.repository.AnalysisRepository;
import iuh.fit.edu.vn.analysisservice.repository.PaymentRepository;
import iuh.fit.edu.vn.analysisservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 5/25/2024
 */
@Service
public class AnalysisService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Analysis> getAllAnalyses() {
        return analysisRepository.findAll();
    }

    public Analysis createAnalysis(Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public double calculateAverageTotalSales() {
        List<Analysis> analyses = analysisRepository.findAll();
        if (analyses.isEmpty()) {
            return 0.0; // hoặc throw một exception nếu không có phân tích nào
        }
        long totalSalesSum = analyses.stream().mapToLong(Analysis::getTotalSales).sum();
        return (double) totalSalesSum / analyses.size();
    }

    public BigDecimal calculateAverageTotalRevenue() {
        List<Analysis> analyses = analysisRepository.findAll();
        if (analyses.isEmpty()) {
            return BigDecimal.ZERO; // hoặc throw một exception nếu không có phân tích nào
        }
        BigDecimal totalRevenueSum = analyses.stream().map(Analysis::getTotalRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalRevenueSum.divide(BigDecimal.valueOf(analyses.size()), 2, RoundingMode.HALF_UP);
    }

}
