package iuh.fit.edu.vn.analysisservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "analysis")
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long shopId;
    private Long totalSales;
    private BigDecimal totalRevenue;
    private Long totalCustomerFeedback;
    private Date analysisDate;

}