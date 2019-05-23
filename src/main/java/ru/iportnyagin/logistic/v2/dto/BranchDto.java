package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.v2.DateTime;
import ru.iportnyagin.logistic.v2.ScheduleItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * BranchDto
 */
@Getter
@RequiredArgsConstructor
public class BranchDto {

    private final String id;
    // режим работы - дата.час открытия отделения и продолжительность работы в часах
    private final List<ScheduleItem> worksSchedule;
    // время на обработку груза - принять, распаковать, оформить, подготовить для дальнейшей отправки
    private final int processingDelay;

    public List<ScheduleItem> findSchedulesItem(DateTime dateTime) {
        return worksSchedule.stream()
                            .filter(s -> s.getDateTime().getDay() == dateTime.getDay())
                            .collect(Collectors.toList());
    }

    public Optional<ScheduleItem> findNextScheduleItem(DateTime dateTime) {
        return worksSchedule.stream()
                            .filter(s -> s.getDateTime().after(dateTime))
                            .min((a, b) -> a.getDateTime().after(b.getDateTime()) ? 1 : -1);
    }

    @Override
    public String toString() {
        return id;
    }

}
