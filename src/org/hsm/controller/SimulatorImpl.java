package org.hsm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static final double ROUND_TO = 10.00;

    private final Random random = new Random();

    @Override
    public Double getOptimalPh(final Plant plant) {
        return plant.getModel().getPH();
    }

    @Override
    public Double getOptimalBrightness(final Plant plant) {
        return plant.getModel().getBrightness();
    }

    @Override
    public Double getOptimalConductibility(final Plant plant) {
        return plant.getModel().getConductivity();
    }

    @Override
    public Double getOptimalTemperature(final Plant plant) {
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
    public List<Double> getPhDays(final Plant plant, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedPh(plant));
        }
        return list;
    }

    @Override
    public List<Double> getBrightnessDays(final Plant plant, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedBrightness(plant));
        }
        return list;
    }

    @Override
    public List<Double> getConductibilityDays(final Plant plant, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedConductibility(plant));
        }
        return list;
    }

    @Override
    public List<Double> getTemperatureDays(final Plant plant, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedTemperature(plant));
        }
        return list;
    }

    private double roundTo(final double value) {
        return Math.round(value * ROUND_TO) / ROUND_TO;
    }

}
