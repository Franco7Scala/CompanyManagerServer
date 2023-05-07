package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;
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
@Table(name = "product_transition")
public class ProductTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "transition_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transitionDate;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @JoinColumn(name = "from_state", referencedColumnName = "name")
    @ManyToOne
    private State fromState;

    @JoinColumn(name = "to_state", referencedColumnName = "name")
    @ManyToOne
    private State toState;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    @JoinColumns({
        @JoinColumn(name = "id_product", referencedColumnName = "id_product"), 
        @JoinColumn(name = "year_product", referencedColumnName = "year")
    })
    @ManyToOne
    private ProductDetail productDetail;

    @Transient
    private int quantityPackages = 0;
    
    @Transient
    private int quantitySingle = 0;
    
    @Transient
    private int newQuantityPackages = 0;
    
    @Transient
    private int newQuantitySingle = 0;

    public ProductTransition() {
    }

    public ProductTransition(Integer id) {
        this.id = id;
    }

    public ProductTransition(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
    
}
