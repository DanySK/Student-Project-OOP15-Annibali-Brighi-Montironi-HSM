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
        Path path = Paths.get(System.getProperty("user.home"), "test.hsm");
        File filename = path.toFile();
       // controller.crateGreenhouse("Test", GreenHouseType.GRID, 450, 1500);
        //controller.createNewPlant("test12", "test botanical", 25, 550, 340, 30, 27, 365, 25);
       // controller.addPlants(1, controller.getDatabase().getPlantModel("test botanical"), 12);

        //controller.saveGreenhouse(filename);

        ControllerImpl controller1 = controller.loadGreenhouse(filename);

        System.out.println(controller1.getDatabase().getPlantModel("test botanical").getName());
    }

}
