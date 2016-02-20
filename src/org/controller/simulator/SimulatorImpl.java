package org.controller.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.Plant;

/**
 *
 * Simulator class: provide a simulates paramters for an Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator {

    private static final double MAXRAND_PH = 0.3;
    private static final double MAXRAND_BRIGHT = 25.0;
    private static final double MAXRAND_COND = 1.0;
    private static final double MAXRAND_TEMP = 0.6;

    private static final double MAXRAND_REAL_PH = 1.5;
    private static final double MAXRAND_REAL_BRIGHT = 900.0;
    private static final double MAXRAND_REAL_COND = 11.0;
    private static final double MAXRAND_REAL_TEMP = 15.0;

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
        for (int i = 0; i < ControllerImpl.getController().getGreenhouse().getFreeSize(); i++) {
            list.add(3.0);
        }

        return null;
    }

    @Override
    public List<Double> getSimulatedPlantGrow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Double> getRealWaterConsuption() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Double> getRealPlantGrow() {
        // TODO Auto-generated method stub
        return null;
    }

    private double roundTo(final double value) {
        return Math.round(value * ROUND_TO) / ROUND_TO;
    }

}
