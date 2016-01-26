package org.hsm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hsm.model.Database;
import org.hsm.model.PlantModel;


/**
 *
 * Simulator class: provide a simulate paramters for a Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator {

    private final Random random = new Random();
    private static final int MAXRAND = 2;
    private final Database db = ControllerImpl.getController().getDatabase();

    private final Map<String, PlantModel> dbMap = db.getDb();

    @Override
    public double getSimulatedPh(final String botanicalName) {
        double j = dbMap.get(botanicalName).getPH() - MAXRAND;
        double n = dbMap.get(botanicalName).getPH() + MAXRAND - j;
        return random.nextDouble() + n;
        //return 0;
    }

    @Override
    public int getSimulatedBrightness(final String botanicalName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSimulatedConductibility(final String botanicalName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSimulatedTemperature(final String botanicalName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Integer> getPhDays(final String botanicalName, final int days) {
        List<Integer> list = new ArrayList<>();
        // int j = PlantImp.getPH(botanicalName) - maxrand;
        // int n = PlantImp.getPH(botanicalName) + maxrand - j;
        // for (int i = 0; i < days; i++) {
        // list.add(random.nextInt(n) + j);
        // }
        return list;
    }

    @Override
    public List<Integer> getBrightnessDays(final String botanicalName, final int days) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getConductibilityDays(final String botanicalName, final int days) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getTemperatureDays(final String botanicalName, final int days) {
        // TODO Auto-generated method stub
        return null;
    }

}
