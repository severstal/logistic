package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iportnyagin.logistic.Config;
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

    @NotNull
    public Optional<Processing> processInBranch(@NotNull final DateTime startAt, final boolean afterShip) {

        if (getProcessingDuration() == 0) {
            return Optional.of(new Processing(this, startAt, startAt));
        }

        DateTime startProcessingAt = startAt;
        int processingDurationRemainder = getProcessingDuration();
        boolean justArrived = afterShip;

        while (processingDurationRemainder > 0) {

            Optional<ScheduleItem> currentOrNextWorkDay = getCurrentOrNextWorkPeriod(startProcessingAt);

            if (!currentOrNextWorkDay.isPresent()) {
                return Optional.empty();
            }

            if (justArrived) {
                justArrived = false;
                if (currentOrNextWorkDay.get().getStartAt()
                                        .hoursBetween(startProcessingAt) > Config.OPENING_BRANCH_WAIT_TIME_IN_HOURS) {
                    return Optional.empty();
                }
            }

            if (processingDurationRemainder > currentOrNextWorkDay.get().getDuration()) {
                processingDurationRemainder = processingDurationRemainder - currentOrNextWorkDay.get().getDuration();
                startProcessingAt = currentOrNextWorkDay.get().getEndAt();
            } else {
                DateTime dateTime = startAt.after(currentOrNextWorkDay.get().getStartAt())
                                    ? startAt
                                    : currentOrNextWorkDay.get().getStartAt();
                return Optional.of(new Processing(this,
                                                  startAt,
                                                  new DateTime(dateTime,
                                                               processingDurationRemainder)));
            }
        }

        throw new RuntimeException("error: point should not be reachable");
    }

    @NotNull
    private Optional<ScheduleItem> getCurrentOrNextWorkPeriod(@NotNull final DateTime startProcessingAt) {

        Optional<ScheduleItem> currentWorkPeriod = worksSchedule.stream()
                                                                .filter(s -> s.getStartAt().before(startProcessingAt)
                                                                        && s.getEndAt().after(startProcessingAt))
                                                                .findFirst();

        if (currentWorkPeriod.isPresent()) {
            return currentWorkPeriod;
        }

        return worksSchedule.stream()
                            .filter(s -> s.getStartAt().after(startProcessingAt))
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
