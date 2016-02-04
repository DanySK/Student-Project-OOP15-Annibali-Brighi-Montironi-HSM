package org.hsm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hsm.model.Database;
import org.hsm.model.PlantModel;

/**
 *
 * Simulator class: provide a simulates paramters for a Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator {

    private static final double MAXRAND_PH = 0.5;
    private static final double MAXRAND_BRIGHT = 50.0;
    private static final double MAXRAND_COND = 2.0;
    private static final double MAXRAND_TEMP = 1.5;

    private static final double ROUND_TO = 100.00;

    private final Random random = new Random();
    private final Database db = ControllerImpl.getController().getDatabase();

    private final Map<String, PlantModel> dbMap = db.getDb();

    @Override
    public double getSimulatedPh(final String botanicalName) {
        double min = dbMap.get(botanicalName).getPH() - MAXRAND_PH;
        double max = dbMap.get(botanicalName).getPH() + MAXRAND_PH;

        return this.roundTo(min + (max - min) * random.nextDouble());

    }

    @Override
    public double getSimulatedBrightness(final String botanicalName) {
        double min = dbMap.get(botanicalName).getBrightness() - MAXRAND_BRIGHT;
        double max = dbMap.get(botanicalName).getBrightness() + MAXRAND_BRIGHT;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedConductibility(final String botanicalName) {
        double min = dbMap.get(botanicalName).getConductivity() - MAXRAND_COND;
        double max = dbMap.get(botanicalName).getConductivity() + MAXRAND_COND;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public double getSimulatedTemperature(final String botanicalName) {
        double min = dbMap.get(botanicalName).getOptimalTemperature() - MAXRAND_TEMP;
        double max = dbMap.get(botanicalName).getOptimalTemperature() + MAXRAND_TEMP;

        return this.roundTo(min + (max - min) * random.nextDouble());
    }

    @Override
    public List<Double> getPhDays(final String botanicalName, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedPh(botanicalName));
        }
        return list;
    }

    @Override
    public List<Double> getBrightnessDays(final String botanicalName, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedBrightness(botanicalName));
        }
        return list;
    }

    @Override
    public List<Double> getConductibilityDays(final String botanicalName, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedConductibility(botanicalName));
        }
        return list;
    }

    @Override
    public List<Double> getTemperatureDays(final String botanicalName, final int days) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(this.getSimulatedTemperature(botanicalName));
        }
        return list;
    }

    private double roundTo(final double value) {
        return Math.round(value * ROUND_TO) / ROUND_TO;
    }

}
