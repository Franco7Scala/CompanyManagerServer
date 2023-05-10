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
public class ProductInTransitionPK {

    @NotNull
    @Column(name = "id_product")
    private int idProduct;

    @NotNull
    @Column(name = "year_product")
    private int yearProduct;

    @NotNull
    @Column(name = "id_transition")
    private int idTransition;

    public ProductInTransitionPK() {}

    public ProductInTransitionPK(int idProduct, int yearProduct, int idTransition) {
        this.idProduct = idProduct;
        this.yearProduct = yearProduct;
        this.idTransition = idTransition;
    }

}
