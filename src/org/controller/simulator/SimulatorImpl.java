package org.controller.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.Plant;

/**
 *
 * Simulator class: provide a simulated paramters for an Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator {

    // Hydroponic values
    private static final double MAXRAND_PH = 0.3;
    private static final double MAXRAND_BRIGHT = 25.0;
    private static final double MAXRAND_COND = 1.0;
    private static final double MAXRAND_TEMP = 0.6;
    private static final double MAXRAND_WATER = 0.9; // in mL
    private static final double MINRAND_WATER = 0.4; // in mL
    private static final int MAXRAND_GROW = 50; // in days
    private static final int MINRAND_GROW = 20; // in days

    // Conventional coltivation values
    private static final double MAXRAND_REAL_PH = 1.5;
    private static final double MAXRAND_REAL_BRIGHT = 900.0;
    private static final double MAXRAND_REAL_COND = 11.0;
    private static final double MAXRAND_REAL_TEMP = 15.0;
    private static final double CONVENTIONAL_WATER_MULTIPLIER = 7; // 70%
    private static final int CONVENTIONAL_GROW_MULTIPLIER = 4; // times than
                                                               // hydroponic

    private static final double ROUND_TO = 10.00;

    private final Random random = new Random();

    @Override
    public double getOptimalPh(final Plant plant) {
        return plant.getModel().getPH();
    }

    @Override
    public double getOptimalBrightness(final Plant plant) {
        return plant.getModel().getBrightness();
    }

    @Override
    public double getOptimalConductibility(final Plant plant) {
        return plant.getModel().getConductivity();
    }

    @Override
    public double getOptimalTemperature(final Plant plant) {
        return plant.getModel().getOptimalTemperature();
    }

    @Override
    public double getSimulatedPh(final Plant plant) {
        double min = getOptimalPh(plant) - MAXRAND_PH;
        double max = getOptimalPh(plant) + MAXRAND_PH;

        return this.roundTo(min + (max - min) * random.nextDouble());

    }

    @Override
    public double getSimulatedBrightness(final Plant plant) {
        double min = getOptimalBrightness(plant) - MAXRAND_BRIGHT;
        double max = getOptimalBrightness(plant) + MAXRAND_BRIGHT;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedConductibility(final Plant plant) {
        double min = getOptimalConductibility(plant) - MAXRAND_COND;
        double max = getOptimalConductibility(plant) + MAXRAND_COND;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedTemperature(final Plant plant) {
        double min = getOptimalTemperature(plant) - MAXRAND_TEMP;
        double max = getOptimalTemperature(plant) + MAXRAND_TEMP;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealPh(final Plant plant) {
        double min = getOptimalPh(plant) - MAXRAND_REAL_PH;
        double max = getOptimalPh(plant) + MAXRAND_REAL_PH;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealBrightness(final Plant plant) {
        double min = getOptimalBrightness(plant) - MAXRAND_REAL_BRIGHT;
        double max = getOptimalBrightness(plant) + MAXRAND_REAL_BRIGHT;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealConductibility(final Plant plant) {
        double min = getOptimalConductibility(plant) - MAXRAND_REAL_COND;
        double max = getOptimalConductibility(plant) + MAXRAND_REAL_COND;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getRealTemperature(final Plant plant) {
        double min = getOptimalTemperature(plant) - MAXRAND_REAL_TEMP;
        double max = getOptimalTemperature(plant) + MAXRAND_REAL_TEMP;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public List<Double> getSimulatedWaterConsuption() {

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(this.roundTo((MINRAND_WATER + (MAXRAND_WATER - MINRAND_WATER)) * random.nextDouble())
                    * ControllerImpl.getController().getRefreshTime());
        }
        return list;
    }

    @Override
    public List<Integer> getSimulatedPlantGrow() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add((MINRAND_GROW + (MAXRAND_GROW - MINRAND_GROW)) * random.nextInt()
                    * ControllerImpl.getController().getRefreshTime());
        }
        return list;
    }

    @Override
    public List<Double> getRealWaterConsuption() {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add(this.roundTo((MINRAND_WATER + (MAXRAND_WATER - MINRAND_WATER)) * random.nextDouble())
                    * ControllerImpl.getController().getRefreshTime() * CONVENTIONAL_WATER_MULTIPLIER);
        }
        return list;
    }

    @Override
    public List<Integer> getRealPlantGrow() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ControllerImpl.getController().getGreenhouse().getUpdateCounter(); i++) {
            list.add((MINRAND_GROW + (MAXRAND_GROW - MINRAND_GROW)) * random.nextInt()
                    * ControllerImpl.getController().getRefreshTime() * CONVENTIONAL_GROW_MULTIPLIER);
        }
        return list;
    }

    private double roundTo(final double value) {
        return Math.round(value * ROUND_TO) / ROUND_TO;
    }

}
