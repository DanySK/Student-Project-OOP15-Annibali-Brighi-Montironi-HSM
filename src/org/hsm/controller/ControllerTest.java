package org.hsm.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hsm.model.GreenHouseType;

public class ControllerTest {

    /**
     *
     * @param args
     *            null
     */
    public static void main(final String[] args) {
        ControllerImpl controller = ControllerImpl.getController();

        Path pathDb = Paths.get(System.getProperty("user.home"), "Db.hsm");
        File filenameDb = pathDb.toFile();
        Path pathGh = Paths.get(System.getProperty("user.home"), "Gh.hsm");
        File filenameGh = pathGh.toFile();

        controller.createGreenhouse("Test", GreenHouseType.GRID, 450, 1500);
        controller.createNewPlant("test12", "test botanical", 25, 550, 340, 30, 27, 365, 25);
        controller.addPlants(1, controller.getDatabase().getPlantModel("test botanical"), 12);

        controller.saveGreenhouse(filenameDb, filenameGh);

        controller.loadGreenhouse(filenameDb, filenameGh);

        System.out.println(controller.getDatabase().getPlantModel("test botanical").getName());
    }

}
