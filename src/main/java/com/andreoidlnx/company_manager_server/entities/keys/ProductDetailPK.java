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
public class ProductDetailPK {

    @NotNull
    @Column(name = "id_product")
    private int idProduct;

    @NotNull
    @Column(name = "year")
    private int year;

    public ProductDetailPK() {}

    public ProductDetailPK(int idProduct, int year) {
        this.idProduct = idProduct;
        this.year = year;
    }
    
}
