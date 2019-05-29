package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.DateTime;
import ru.iportnyagin.logistic.ScheduleItem;

import java.util.List;
import java.util.Optional;

/**
 * Branch - отделение
 * worksSchedule - график работы (дата.час открытия отделения и продолжительность работы в часах)
 * processingDuration - время на обработку одного груза (принять, распаковать, оформить, подготовить для дальнейшей отправки)
 */
@Getter
@RequiredArgsConstructor
public class Branch {

    private final String id;
    private final List<ScheduleItem> worksSchedule;
    private final int processingDuration;

    public Optional<Processing> processInBranch(DateTime startAt) {

        DateTime startProcessingAt = startAt;
        int processingDuration = getProcessingDuration();

        if (processingDuration == 0) {
            return Optional.of(new Processing(this, startAt, startAt));
        }

        while (processingDuration > 0) {

            Optional<ScheduleItem> currentOrNextWorkDay = getCurrentOrNextWorkDay(startProcessingAt);

            if (!currentOrNextWorkDay.isPresent()) {
                return Optional.empty();
            }

            if (processingDuration > currentOrNextWorkDay.get().getDuration()) {
                processingDuration = processingDuration - currentOrNextWorkDay.get().getDuration();
                startProcessingAt = currentOrNextWorkDay.get().getEndAt();
            } else {
                DateTime dateTime = startAt.after(currentOrNextWorkDay.get().getStartAt())
                                    ? startAt
                                    : currentOrNextWorkDay.get().getStartAt();
                return Optional.of(new Processing(this,
                                                  startAt,
                                                  new DateTime(dateTime,
                                                               processingDuration)));
            }
        }

        throw new RuntimeException("error: point should not be reachable");
    }

    private Optional<ScheduleItem> getCurrentOrNextWorkDay(DateTime dateTime) {

        Optional<ScheduleItem> current = worksSchedule.stream()
                                                      .filter(s -> s.getStartAt().before(dateTime)
                                                              && s.getEndAt().after(dateTime))
                                                      .findFirst();

        if (current.isPresent()) {
            return current;
        }

        return worksSchedule.stream()
                            .filter(s -> s.getStartAt().after(dateTime))
                            .min(new ScheduleItem.StartAtComparator());
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        return id != null ? id.equals(branch.id) : branch.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
