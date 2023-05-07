package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "receipt")
public class Receipt {

    @Column(name = "total")
    private float total;

    @Column(name = "total_discounted")
    private float totalDiscounted;

    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne
    private User user;

    @Column(name = "discount")
    private Integer discount = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cash_register_number")
    private Integer cashRegisterNumber;

    @Column(name = "cash_register_fresher", length = 50)
    private String cashRegisterFresher;

    @Column(name = "receipt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiptDate;
    
    @Column(name = "daily_number")
    private Integer dailyNumber;
    
    @Column(name = "terms_of_payment", length = 50)
    private String termsOfPayment;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "receipt")
    private List<ProductInReceipt> productInReceiptList;

    public Receipt() {}

    public Receipt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
}
