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
@Table(name = "product_state")
public class ProductState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumns({
        @JoinColumn(name = "id_product", referencedColumnName = "id_product"), 
        @JoinColumn(name = "year", referencedColumnName = "year")
    })
    @ManyToOne
    private ProductDetail productDetail;

    @JoinColumn(name = "state", referencedColumnName = "name")
    @ManyToOne
    private State state;

    @Column(name = "quantity")
    private Integer quantity;

    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProductState() {
    }

    public ProductState(int id) {
        this.id = id;
    }
    
    public ProductState(Integer id) {
        this.id = id;
    }

    public ProductState(int idProduct, int year, String state, int quantity) {
        product = new Product(idProduct);
        productDetail = new ProductDetail(idProduct, year);
        this.state = new State(state);
        this.quantity = quantity;
    }

}
