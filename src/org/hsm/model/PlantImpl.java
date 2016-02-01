package org.hsm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlantImpl implements Plant, Serializable {
    private final PlantModel model;
    private final int cost;
    private final List<Double> phList;
    private final List<Double> brightList;
    private final List<Double> conductList;
    private final List<Double> tempList;

    public PlantImpl(final PlantModel model,final int cost) {
        super();
        this.model = model;
        this.cost = cost;

        this.phList = new LinkedList<>();
        this.brightList = new LinkedList<>();
        this.conductList = new LinkedList<>();
        this.tempList = new LinkedList<>();
    }

    @Override
    public void addPhValue(final double value) {
        this.phList.add(0, value);
    }

    @Override
    public PlantModel getModel() {
        return model;
    }

    @Override
    public int getCost() {
        return cost;
    }
    
    @Override
    public List<Double> getPhList() {
        return phList;
    }

    @Override
    public List<Double> getBrightList() {
        return brightList;
    }

    @Override
    public List<Double> getConductList() {
        return conductList;
    }

    @Override
    public List<Double> getTempList() {
        return tempList;
    }

    @Override
    public void addBrightValue(final double value) {
        this.brightList.add(0, value);
    }

    @Override
    public void addConductValue(final double value) {
        this.conductList.add(0, value);
    }

    @Override
    public void addTempValue(final double value) {
        this.tempList.add(0, value);
    }


    @Override
    public String toString() {
        return "PlantImpl [model=" + model + ", cost=" + cost + ", phList=" + phList + ", brightList=" + brightList
                + ", conductList=" + conductList + ", tempList=" + tempList + "]";
    }




}
