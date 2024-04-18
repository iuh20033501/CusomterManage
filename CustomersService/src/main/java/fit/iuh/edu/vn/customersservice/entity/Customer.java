package fit.iuh.edu.vn.customersservice.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="customers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @Column(name ="id")
    private String idCustomer;
    @Column(name ="name")
    private String customerName;
    @Column(name ="phone")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name="deleted")
    private int deleted;

    public Customer(String idCustomer, String customerName, String phoneNumber, String email) {
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer(String idCustomer, String customerName, String phoneNumber, String email, int deleted) {
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.deleted = deleted;
    }

    public Customer(String customerName, String phoneNumber, String email, int deleted) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.deleted = deleted;
    }
}
