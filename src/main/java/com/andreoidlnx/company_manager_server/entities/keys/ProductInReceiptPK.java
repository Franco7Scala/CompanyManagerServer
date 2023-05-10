package com.andreoidlnx.company_manager_server.entities.keys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "receipt_id")
    private int receiptId;

    @NotNull
    @Column(name = "product_id")
    private int productId;

    @NotNull
    @Column(name = "product_year")
    private int productYear;

    public ProductInReceiptPK() {}

    public ProductInReceiptPK(int receiptId, int productId, int productYear) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.productYear = productYear;
    }

}
