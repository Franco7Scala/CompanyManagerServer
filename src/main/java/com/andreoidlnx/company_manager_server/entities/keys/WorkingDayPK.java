package com.andreoidlnx.company_manager_server.entities.keys;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Embeddable
public class WorkingDayPK {

    @NotNull
    @Column(name = "id_user")
    private int idUser;

    @NotNull
    @Column(name = "work_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workDate;

    public WorkingDayPK() {}

    public WorkingDayPK(int idUser, Date workDate) {
        this.idUser = idUser;
        this.workDate = workDate;
    }

}
