package org.hsm.controller.simulator;

import static org.junit.Assert.assertTrue;

import org.hsm.controller.Controller;
import org.hsm.controller.ControllerImpl;
import org.hsm.model.plant.Plant;
import org.junit.Test;

/**
 *
 * Class to test Simulator.
 *
 */
public class SimulatorTest {

    /**
     * Test Simulator values.
     */
    @Test
    // CHECKSTYLE:OFF: checkstyle:magicnumber
    public void testSimulator() {

        final Controller cont = ControllerImpl.getController();
        cont.createGreenhouse("Test", "Grid", 120, 100);
        cont.createNewPlant("Lettuce", "Lactuca sativa", 6, 2500, 11, 28, 23, 35, 250);
        final Plant plant = cont.getGreenhouse().getPlants().get("Lactuca sativa");
        final Simulator sim = new SimulatorImpl();

        System.out.println(sim.getSimulatedBrightness(plant));
        System.out.println(sim.getSimulatedPh(plant));

        // Test Parametri nei margini
        for (int i = 0; i < 1000; i++) {
            assertTrue("PH", sim.getSimulatedPh(plant) >= 5.50 && sim.getSimulatedPh(plant) <= 6.50);
            assertTrue("Brightness",
                    sim.getSimulatedBrightness(plant) >= 2450 && sim.getSimulatedBrightness(plant) <= 2550);
            assertTrue("Conductibility",
                    sim.getSimulatedConductibility(plant) >= 9 && sim.getSimulatedConductibility(plant) <= 13);
            assertTrue("Temperature",
                    sim.getSimulatedTemperature(plant) >= 21.5 && sim.getSimulatedTemperature(plant) <= 24.5);
        }

    }

}
