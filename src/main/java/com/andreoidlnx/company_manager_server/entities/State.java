package com.andreoidlnx.company_manager_server.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;

    public State() {}

    public State(String name) {
        this.name = name;
    }
    
}
