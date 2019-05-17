package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.CargoDto;
import ru.iportnyagin.logistic.v2.dto.DateTime;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Config config = new ConfigImpl();
        CargoDto cargo = new CargoDto(config.getBranches().get(4));
        Processor processor = new Processor(config);

        Optional<Path> bestPath = processor.findBestPath(cargo,
                                                         config.getBranches().get(0),
                                                         DateTime.getCurrentDate(),
                                                         DateTime.getCurrentDate().addHour(120));

        System.out.println("");
        System.out.println(bestPath.map(Path::getRoutes));
    }


}
