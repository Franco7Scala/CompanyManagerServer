package com.andreoidlnx.company_manager_server.entities;

import java.util.List;

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
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "bar_code_single", length = 100)
    private String barCodeSingle;

    @Column(name = "bar_code_package", length = 100)
    private String barCodePackage;

    @Column(name = "description", length = 130)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductDetail> productDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductState> productStateList;

    public Product() {}

    public Product(Integer id) {
        this.id = id;
    }

}
