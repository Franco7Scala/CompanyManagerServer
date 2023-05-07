package com.andreoidlnx.company_manager_server.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProductStatePK{

    @Column(name = "id_product", nullable = false)
    private int idProduct;

    @Column(name = "year", nullable = false)
    private int year;

    public ProductStatePK() {}

    public ProductStatePK(int idProduct, int year) {
        this.idProduct = idProduct;
        this.year = year;
    }
    
}
