package com.andreoidlnx.company_manager_server.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProductInTransitionPK {

    @Column(name = "id_product", nullable = false)
    private int idProduct;

    @Column(name = "year_product", nullable = false)
    private int yearProduct;

    @Column(name = "id_transition", nullable = false)
    private int idTransition;

    public ProductInTransitionPK() {}

    public ProductInTransitionPK(int idProduct, int yearProduct, int idTransition) {
        this.idProduct = idProduct;
        this.yearProduct = yearProduct;
        this.idTransition = idTransition;
    }

}
