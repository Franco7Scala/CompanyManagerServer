package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;

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
@Table(name = "stuff_transition")
public class StuffTransition {

    @Column(name = "quantity")
    private Float quantity;
    
    @Column(name = "transition_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transitionDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", length = 20)
    private String type;

    @JoinColumn(name = "id_stuff", referencedColumnName = "id")
    @ManyToOne
    private Stuff idStuff;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public StuffTransition() {}

    public StuffTransition(Integer id) {
        this.id = id;
    }
    
}
