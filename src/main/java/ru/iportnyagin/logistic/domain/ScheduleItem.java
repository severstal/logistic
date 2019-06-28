package ru.iportnyagin.logistic.domain;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ScheduleItem - элемент расписания
 */
@Entity
@Table(name = "schedule_item")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScheduleItem implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "schedule_id", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private long scheduleId;

    @Column
    private int day;

    @Column
    private int hour;

    @Column
    private int duration;

}





