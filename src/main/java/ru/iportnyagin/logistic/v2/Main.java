package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.CargoDto;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Config config = new ConfigImpl();
        CargoDto cargo = new CargoDto(config.getBranches().get(4));
        Processor processor = new ProcessorImpl(config);

        Optional<Path> bestPath = processor.findBestPath(cargo,
                                                         config.getBranches().get(0),
                                                         new DateTime(0 ,5),
                                                         100);

        System.out.println();
        System.out.println("best path: " + bestPath.map(Path::toString).orElse("no result"));
    }


}
