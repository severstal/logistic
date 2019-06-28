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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Route - маршрут, откуда, куда, расписание
 */
@Entity
@Table(name = "route")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Route implements java.io.Serializable {

    @Id
    @Column(name = "route_id", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String routeId;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false, insertable = false, updatable = false)
    private Branch fromBranch;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false, insertable = false, updatable = false)
    private Branch toBranch;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "route_schedule",
            joinColumns = {@JoinColumn(name = "route_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "schedule_id", nullable = false, updatable = false)})
    private Set<ScheduleItem> scheduleItems;

}
