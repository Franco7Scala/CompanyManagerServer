package com.andreoidlnx.company_manager_server.entities;

import java.util.LinkedList;
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
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", length = 20) 
    private String username;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "visible")
    private boolean visible = true; 

    @Column(name = "salary_per_day")
    private float salaryPerDay;

    @OneToMany(mappedBy = "user")
    private List<Receipt> receiptList;

    @OneToMany(mappedBy = "idUser")
    private List<ProductTransition> productTransitionList;

    @OneToMany(mappedBy = "idUser")
    private List<StuffTransition> stuffTransitionList;

    @JoinTable(name = "user_capability", joinColumns = {
        @JoinColumn(name = "id_user", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "name_capability", referencedColumnName = "name")
    })
    @ManyToMany
    private List<Capability> capabilityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<WorkingDay> workingDayList;

    public User() {
        capabilityList = new LinkedList<>();
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public boolean hasCapabilityNamed(String capabilityName) {
        return capabilityList.contains(new Capability(capabilityName, capabilityName));
    }

}
