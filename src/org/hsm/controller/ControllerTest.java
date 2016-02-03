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

        String filenameGh = System.getProperty("user.home") + "Gh.hsm";
        String filenameDb = System.getProperty("user.home") + "Db.dat";

        controller.createGreenhouse("Test", GreenHouseType.GRID, 450, 1500);
        controller.createNewPlant("test12", "test botanical", 25, 550, 340, 30, 27, 365, 25);
        controller.addPlant(controller.getDatabase().getPlantModel("test botanical"), 12);

        controller.saveGreenhouse();
        controller.saveDatabase();

        controller.loadGreenhouse();
        controller.loadDatabase();

        System.out.println(controller.getDatabase().getPlantModel("test botanical").getName());
    }

}
