package com.andreoidlnx.company_manager_server.entities;

import java.util.Date;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class WorkingDayPK {

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "work_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date workDate;

    public WorkingDayPK() {}

    public WorkingDayPK(int idUser, Date workDate) {
        this.idUser = idUser;
        this.workDate = workDate;
    }

}
