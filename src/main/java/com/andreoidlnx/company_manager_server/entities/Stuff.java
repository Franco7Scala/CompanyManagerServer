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
@Table(name = "stuff")
public class Stuff implements Comparable<Stuff> {

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "threshold")
    private float threshold;

    @Column(name = "visible")
    private Boolean visible = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "unit_measure", length = 10)
    private String unitMeasure;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "bar_code_single", length = 100)
    private String barCodeSingle;

    @Column(name = "bar_code_package", length = 100)
    private String barCodePackage;
    
    @OneToMany(mappedBy = "idStuff")
    private List<StuffTransition> stuffTransitionList;

    public Stuff() {
    }

    public Stuff(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Stuff o) {
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }
    
}
