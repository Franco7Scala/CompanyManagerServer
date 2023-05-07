package com.andreoidlnx.company_manager_server.entities;

import java.util.List;
import jakarta.persistence.*;
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
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "value", length = 100)
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
