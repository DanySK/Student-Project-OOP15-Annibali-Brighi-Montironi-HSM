package org.hsm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Simulator class: provide a simulate paramters for a Hydroponic Greenhouse.
 *
 */
public class SimulatorImpl implements Simulator {

    private Random random = new Random();
    private static int maxrand = 2;

    @Override
    public int getSimulatedPh(final String botanicalName) {
        // int j = PlantImp.getPH(botanicalName) - maxrand;
        // int n = PlantImp.getPH(botanicalName) + maxrand - j;
        // return random.nextInt(n) + j;
        return 0;
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
//        int j = PlantImp.getPH(botanicalName) - maxrand;
//        int n = PlantImp.getPH(botanicalName) + maxrand - j;
//        for (int i = 0; i < days; i++) {
//            list.add(random.nextInt(n) + j);
//        }
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
