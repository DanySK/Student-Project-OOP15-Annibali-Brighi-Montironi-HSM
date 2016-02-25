package org.hsm.controller;

import org.hsm.model.db.Database;

/**
 *
 * Class for test the application.
 *
 */
public class ApplicationTest {

    /**
     *
     */
    // CHECKSTYLE:OFF:
    public ApplicationTest() {
        final Controller controller = ControllerImpl.getController();
        final Database db = controller.getDatabase();

        controller.createGreenhouse("Greenhouse Test", "Grid", 400, 50);

        controller.createNewPlant("Onion", "Allium cepa", 6, 4000, 16, 6, 23, 90, 230);
        controller.createNewPlant("Eggplant", "Solanum melongena", 6, 6000, 6, 80, 160, 28, 280);
        controller.createNewPlant("Turnip", "Brassica rapa", 6, 5300, 3, 45, 90, 20, 240);
        controller.createNewPlant("Carrots", "Daucus carota", 6, 5300, 6, 65, 75, 18, 250);
        controller.createNewPlant("Tomato", "Solanum lycopersicum", 6, 5100, 6, 70, 220, 35, 250);
        controller.createNewPlant("Watermelon", "Citrullus lanatus", 6, 3500, 4, 110, 500, 20, 350);
        controller.createNewPlant("Cabbage", "Brassica oleracea (capitata)", 6, 5300, 6, 100, 300, 26, 270);
        controller.createNewPlant("Basil", "Ocimum basilicum", 6, 5000, 7, 70, 90, 12, 210);
        controller.createNewPlant("Squash blossoms", "Cucurbita (winter)", 6, 4200, 4, 65, 130, 20, 350);
        controller.createNewPlant("Strawberrie", "Fragaria × ananassa", 6, 5600, 10, 120, 110, 20, 270);
        controller.createNewPlant("Cucumber", "Cucumis sativus", 6, 5300, 3, 50, 120, 20, 330);
        controller.createNewPlant("Spinach", "Spinacia oleracea", 6, 5100, 5, 45, 300, 20, 210);
        controller.createNewPlant("Blueberry", "Vaccinium myrtilloides", 5, 4500, 5, 60, 120, 19, 220);
        controller.createNewPlant("Broccoli", "Brassica oleracea", 6, 5400, 5, 65, 100, 31, 250);
        controller.createNewPlant("Artichoke", "Cynara cardunculus", 7, 5000, 15, 80, 220, 12, 210);
        controller.createNewPlant("Bean (Common)", "Phaseolus vulgaris", 6, 5100, 7, 50, 30, 30, 240);
        controller.createNewPlant("Pumpkin", "Cucurbita", 6, 5800, 4, 90, 500, 22, 360);
        controller.createNewPlant("Pea", "Pisum sativum", 6, 4600, 6, 60, 120, 12, 230);
        controller.createNewPlant("Leek", "Allium porrum", 7, 5200, 7, 120, 60, 16, 260);
        controller.createNewPlant("Bell Pepper", "Capsicum annuum", 6, 6000, 8, 75, 230, 25, 260);
        controller.createNewPlant("Radish", "Raphanus sativus", 6, 4000, 4, 30, 85, 18, 240);
        controller.createNewPlant("Lettuce", "Lactuca sativa", 6, 6300, 3, 53, 380, 10, 230);

        controller.addPlants(db.getPlantModel("Allium cepa"), 6, 5);
        controller.addPlants(db.getPlantModel("Daucus carota"), 3, 4);
        controller.addPlants(db.getPlantModel("Ocimum basilicum"), 4, 2);
        controller.addPlants(db.getPlantModel("Solanum lycopersicum"), 5, 20);
        controller.addPlants(db.getPlantModel("Fragaria × ananassa"), 7, 10);
        controller.addPlants(db.getPlantModel("Capsicum annuum"), 6, 5);
        controller.addPlants(db.getPlantModel("Lactuca sativa"), 3, 15);
    }

}
