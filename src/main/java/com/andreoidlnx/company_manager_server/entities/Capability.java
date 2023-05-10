package com.andreoidlnx.company_manager_server.entities;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "capability")
public class Capability {

    @Id
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;

    @Size(max = 100)
    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "capabilityList")
    private List<User> userList;

    public Capability() {}

    public Capability(String name) {
        this.name = name;
    }

    public Capability(String name, String value) {
        this.name = name;
        this.value = value;
    } 

    public String toString(){
        return name;
    }

}
