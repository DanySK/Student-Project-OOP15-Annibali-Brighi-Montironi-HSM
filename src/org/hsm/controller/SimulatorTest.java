package org.hsm.controller;


import static org.junit.Assert.assertTrue;


import org.hsm.model.GreenHouseType;
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
    //CHECKSTYLE:OFF: checkstyle:magicnumber
    public void testSimulator() {

        final Controller cont = ControllerImpl.getController();
        cont.createGreenhouse("Test", GreenHouseType.GRID, 120, 100);
        cont.createNewPlant("Lettuce", "Lactuca sativa", 6, 2500, 11, 28, 23, 35, 250);
        final Simulator sim = new SimulatorImpl();

        System.out.println(sim.getSimulatedBrightness("Lactuca sativa"));
        System.out.println(sim.getSimulatedPh("Lactuca sativa"));

        // Test Parametri nei margini
        for (int i = 0; i < 1000; i++) {
            assertTrue("PH", sim.getSimulatedPh("Lactuca sativa") >= 5.50 && sim.getSimulatedPh("Lactuca sativa") <= 6.50);
            assertTrue("Brightness", sim.getSimulatedBrightness("Lactuca sativa") >= 2450 && sim.getSimulatedBrightness("Lactuca sativa") <= 2550);
            assertTrue("Conductibility", sim.getSimulatedConductibility("Lactuca sativa") >= 9 && sim.getSimulatedConductibility("Lactuca sativa") <= 13);
            assertTrue("Temperature", sim.getSimulatedTemperature("Lactuca sativa") >= 21.5 && sim.getSimulatedTemperature("Lactuca sativa") <= 24.5);
        }




    }

}
