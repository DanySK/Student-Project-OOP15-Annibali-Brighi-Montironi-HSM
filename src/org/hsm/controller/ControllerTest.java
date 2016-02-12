package org.hsm.controller;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Test class for Controller.
 *
 */
public class ControllerTest {

    /**
     *
     * Test method.
     *
     */
    //CHECKSTYLE:OFF: checkstyle:magicnumber
    @Test
    public void controllerTeset() {
        ControllerImpl controller = ControllerImpl.getController();

        controller.createGreenhouse("Test", "Grid", 450, 1500);
        controller.createNewPlant("name", "botanical name", 25, 550, 340, 30, 27, 365, 25);
        controller.addPlants(controller.getDatabase().getPlantModel("botanical name"), 12, 2);

        controller.saveGreenhouse();
        controller.saveDatabase();

        controller.deleteGreenhouse();

        controller.loadGreenhouse();
        controller.loadDatabase();

        assertTrue(controller.getDatabase().getPlantModel("botanical name").getName().equals("name"));
        assertFalse(controller.getDatabase().getPlantModel("botanical name").getName().equals("nameerror"));


    }

}
