package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;

import com.andreoidlnx.company_manager_server.entities.keys.WorkingDayPK;

import jakarta.persistence.*;
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
@Table(name = "working_day")
public class WorkingDay {
    
    @Column(name = "visible")
    private boolean visible = true;

    @EmbeddedId
    protected WorkingDayPK workingDayPK;

    @Column(name = "in_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inTime;
    
    @Column(name = "out_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outTime;

    @Size(max = 30)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public WorkingDay() {
        workingDayPK = new WorkingDayPK();
    }

    public WorkingDay(WorkingDayPK workingDayPK) {
        this.workingDayPK = workingDayPK;
    }

    public WorkingDay(int idUser, Date workDate) {
        this.workingDayPK = new WorkingDayPK(idUser, workDate);
    }

}
