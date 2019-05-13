package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.dto.CargoDto;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Config config = new ConfigImpl();
        CargoDto cargo = new CargoDto(config.getBranches().get(0));
        Processor processor = new Processor(config);

        Optional<Path> bestPath = processor.findBestPath(cargo, config.getBranches().get(4));

        System.out.println("");
        System.out.println(bestPath.map(Path::getRoutes));
    }


}
