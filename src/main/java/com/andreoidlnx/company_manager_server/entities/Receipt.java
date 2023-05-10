package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Column(name = "cash_register_number")
    private Integer cashRegisterNumber;

    @Size(max = 50)
    @Column(name = "cash_register_fresher")
    private String cashRegisterFresher;

    @Column(name = "receipt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiptDate;
    
    @Column(name = "daily_number")
    private Integer dailyNumber;
    
    @Size(max = 50)
    @Column(name = "terms_of_payment")
    private String termsOfPayment;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "receipt")
    private List<ProductInReceipt> productInReceiptList;

    public Receipt() {}

    public Receipt(Integer id) {
        this.id = id;
    }
    
}
