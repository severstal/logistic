package ru.iportnyagin.logistic;

import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Cargo;
import ru.iportnyagin.logistic.entity.Path;
import ru.iportnyagin.logistic.entity.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * PathSearchTest.
 *
 * @author portnyagin
 */
public class PathSearchTest {


    private Config config = new TestConfig();

    private PathSearch search = new FastPathSearch(config);

    @Test
    public void test() {

        Cargo cargo = new Cargo(config.getBranches().get(1));

        Optional<Path> bestPath = search.find(cargo,
                                              config.getBranches().get(0),
                                              new DateTime(0, 0));

        Assert.assertTrue(bestPath.isPresent());
    }

}

@Getter
class TestConfig implements Config {

    private List<Branch> branches;
    private List<Route> routes;

    public TestConfig() {

        final Branch branchA = new Branch("A",
                                          ScheduleBuilder.builder() // круглосуточно без перерывов и выходных
                                                         .dateTime(new DateTime(0, 0))
                                                         .intValue(24)
                                                         .repeatPeriodInHour(24)
                                                         .repeatCount(365)
                                                         .build(),
                                          3);

        final Branch branchB = new Branch("B",
                                          ScheduleBuilder.builder()
                                                         .hour(8)
                                                         .intValue(10)
                                                         .buildWorkDaysForYear(),
                                          2);

        branches = Arrays.asList(branchA, branchB);

        routes = Arrays.asList(new Route("ab",
                                         branchA,
                                         branchB,
                                         ScheduleBuilder.builder()
                                                        .dateTime(new DateTime(0, 7))
                                                        .intValue(5)
                                                        .repeatPeriodInHour(24)
                                                        .repeatCount(365)
                                                        .build())
        );
    }
}