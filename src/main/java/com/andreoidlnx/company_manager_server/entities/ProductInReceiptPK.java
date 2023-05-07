package com.andreoidlnx.company_manager_server.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Embeddable
public class ProductInReceiptPK {

    @Column(name = "receipt_id", nullable = false)
    private int receiptId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "product_year", nullable = false)
    private int productYear;

    public ProductInReceiptPK() {
    }

    public ProductInReceiptPK(int receiptId, int productId, int productYear) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.productYear = productYear;
    }

}
