package fit.iuh.edu.vn.staffsservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="staffs")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Staff {
    @Id
    @Column(name ="staffID")
    private String id;
    @Column(name ="password")
    private String password;
    @Column(name ="name")
    private String staffName;
    @Column(name ="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name ="status")
    private int status;
    @Column(name ="role")
    private String role;

    public Staff(String id, String password, String staffName, String phone, String email, int status, String role) {
        this.id = id;
        this.password = password;
        this.staffName = staffName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public Staff(String password, String staffName, String phone, String email, String role) {
        this.password = password;
        this.staffName = staffName;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }
}
