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
@Table(name = "state")
public class State {

    @Column(name = "downloadable")
    private Boolean downloadable = true;

    @OneToMany(mappedBy = "fromState")
    private List<ProductTransition> productTransitionList;
    
    @OneToMany(mappedBy = "toState")
    private List<ProductTransition> productTransitionList1;

    @OneToMany(mappedBy = "state")
    private List<ProductState> productStateList;

    @Column(name = "visible")
    private Boolean visible = true;

    @Column(name = "favourite")
    private Boolean favourite = false;

    @Id
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    public State() {}

    public State(String name) {
        this.name = name;
    }
    
}
