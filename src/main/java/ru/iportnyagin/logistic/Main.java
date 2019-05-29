package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.entity.Cargo;
import ru.iportnyagin.logistic.entity.Path;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        String configString = new ResourceFile("config.json").read();

        Config config = new JsonConfig(configString);

        System.out.println(config);

        Cargo cargo = new Cargo(config.getBranches().get(4));
        PathSearch pathSearch = new FastPathSearch(config);

        Optional<Path> bestPath = pathSearch.find(cargo,
                                                  config.getBranches().get(0),
                                                  new DateTime(0, 5));

        System.out.println();
        System.out.println("best path: " + bestPath.map(Path::toString).orElse("no result"));
    }

}
