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
@Entity
@Table(name = "product_in_receipt")
public class ProductInReceipt {

    @EmbeddedId
    protected ProductInReceiptPK productInReceiptPK;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;

    @JoinColumn(name = "receipt_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receipt receipt;

    @JoinColumns({
        @JoinColumn(name = "product_id", referencedColumnName = "id_product", insertable = false, updatable = false), 
        @JoinColumn(name = "product_year", referencedColumnName = "year", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private ProductDetail productDetail;

    public ProductInReceipt() {
    }

    public ProductInReceipt(ProductInReceiptPK productInReceiptPK) {
        this.productInReceiptPK = productInReceiptPK;
    }

    public ProductInReceipt(int receiptId, int productId, int productYear) {
        this.productInReceiptPK = new ProductInReceiptPK(receiptId, productId, productYear);
    }

    public ProductInReceiptPK getProductInReceiptPK() {
        return productInReceiptPK;
    }
    
}
