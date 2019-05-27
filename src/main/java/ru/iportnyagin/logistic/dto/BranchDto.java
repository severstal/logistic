package ru.iportnyagin.logistic.dto;

import lombok.NoArgsConstructor;
import ru.iportnyagin.logistic.entity.Branch;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BranchDto - отделение
 * worksSchedule - график работы (дата.час открытия отделения и продолжительность работы в часах)
 * processingDuration - время на обработку одного груза (принять, распаковать, оформить, подготовить для дальнейшей отправки)
 */
@NoArgsConstructor
public class BranchDto {

    private String id;
    private List<ScheduleItemDto> worksSchedule;
    private int processingDuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ScheduleItemDto> getWorksSchedule() {
        return worksSchedule;
    }

    public void setWorksSchedule(List<ScheduleItemDto> worksSchedule) {
        this.worksSchedule = worksSchedule;
    }

    public int getProcessingDuration() {
        return processingDuration;
    }

    public void setProcessingDuration(int processingDuration) {
        this.processingDuration = processingDuration;
    }

    public Branch toBranch() {
        return new Branch(id,
                          worksSchedule.stream()
                                       .map(s -> s.toScheduleItem())
                                       .collect(Collectors.toList()),
                          processingDuration);
    }
}
