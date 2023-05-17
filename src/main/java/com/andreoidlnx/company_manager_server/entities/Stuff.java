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
@Table(name = "stuff")
public class Stuff implements Comparable<Stuff> {

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "threshold")
    private float threshold;

    @Column(name = "visible")
    private boolean visible = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private int id;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @Size(max = 10)
    @Column(name = "unit_measure")
    private String unitMeasure;

    @Size(max = 300)
    @Column(name = "description")
    private String description;

    @Size(max = 100)
    @Column(name = "bar_code_single")
    private String barCodeSingle;

    @Size(max = 100)
    @Column(name = "bar_code_package")
    private String barCodePackage;
    
    @OneToMany(mappedBy = "idStuff")
    private List<StuffTransition> stuffTransitionList;

    public Stuff() {}

    public Stuff(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Stuff o) {
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }
    
}
