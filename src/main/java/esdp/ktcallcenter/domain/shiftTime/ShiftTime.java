package esdp.ktcallcenter.domain.shiftTime;

import esdp.ktcallcenter.domain.region.Region;
import esdp.ktcallcenter.domain.shift.Shift;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "shift_times")
public class ShiftTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shift shift;

    @Column
    private Time startTime ;

    @Column
    private Time endTime ;

}
