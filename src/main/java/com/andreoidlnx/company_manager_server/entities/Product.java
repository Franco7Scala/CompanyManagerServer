package com.andreoidlnx.company_manager_server.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Column(name = "id")
    private int id;

    @Size(max = 20)
    @Column(name = "code")
    private String code;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @Size(max = 100)
    @Column(name = "bar_code_single")
    private String barCodeSingle;

    @Size(max = 100)
    @Column(name = "bar_code_package")
    private String barCodePackage;

    @Size(max = 130)
    @Column(name = "description")
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
