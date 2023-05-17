package com.andreoidlnx.company_manager_server.entities;

import java.util.LinkedList;
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
@Table(name = "user")
public class User {
    
    @Column(name = "salary_per_day")
    private float salaryPerDay;

    @OneToMany(mappedBy = "user")
    private List<Receipt> receiptList;

    @Size(max = 150)
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "idUser")
    private List<ProductTransition> productTransitionList;

    @OneToMany(mappedBy = "idUser")
    private List<StuffTransition> stuffTransitionList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private int id;

    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @Size(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 30)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @Size(max = 100)
    @Column(name = "code")
    private String code;

    @JoinTable(name = "user_capability", joinColumns = {
        @JoinColumn(name = "id_user", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "name_capability", referencedColumnName = "name")
    })
    @ManyToMany
    private List<Capability> capabilityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<WorkingDay> workingDayList;

    @Column(name = "visible")
    private boolean visible = true;

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
