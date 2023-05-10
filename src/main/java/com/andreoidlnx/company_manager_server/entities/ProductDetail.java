package com.andreoidlnx.company_manager_server.entities;

import java.util.LinkedList;
import java.util.List;
import com.andreoidlnx.company_manager_server.entities.keys.ProductDetailPK;
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
@Table(name = "product_detail")
public class ProductDetail {

    @Column(name = "price_reseller")
    private float priceReseller;

    @Column(name = "price_private")
    private float pricePrivate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productDetail")
    private List<ProductInReceipt> productInReceiptList;

    @OneToMany(mappedBy = "productDetail")
    private List<ProductTransition> productTransitionList;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.PERSIST)
    private List<ProductState> productStateList;

    @EmbeddedId
    protected ProductDetailPK productDetailPK;

    @Column(name = "quantity")
    private Integer quantity;

    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProductDetail() {
        productDetailPK = new ProductDetailPK();
        productStateList = new LinkedList<>();
        quantity = 0;
    }

    public ProductDetail(int year) {
        productDetailPK = new ProductDetailPK();
        productDetailPK.setYear(year);
        productStateList = new LinkedList<>();
        quantity = 0;
    }

    public ProductDetail(ProductDetailPK productDetailPK) {
        this.productDetailPK = productDetailPK;
        productStateList = new LinkedList<>();
    }

    public ProductDetail(int idProduct, int year) {
        this.productDetailPK = new ProductDetailPK(idProduct, year);
        productStateList = new LinkedList<>();
    }

}
