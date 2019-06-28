package ru.iportnyagin.logistic.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Branch - отделение
 */
@Entity
@Table(name = "branch")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Branch implements java.io.Serializable {

    @Id
    @Column(name = "branch_id", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String branchId;

    @Column(name = "description")
    private String description;

    @Column(name = "processing_duration")
    private int processingDuration;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "branch_schedule",
            joinColumns = {@JoinColumn(name = "branch_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "schedule_id", nullable = false, updatable = false)})
    private Set<ScheduleItem> scheduleItems = new HashSet<>();

}
