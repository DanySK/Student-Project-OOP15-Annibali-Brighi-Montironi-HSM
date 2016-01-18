package org.hsm.model;

import java.util.LinkedList;
import java.util.List;

public class PlantImpl implements Plant {
    private final PlantModel model;
    private final int cost;
    private final List<Double> phList;
    private final List<Double> brightList;
    private final List<Double> conductList;
    private final List<Double> tempList;

    public PlantImpl(PlantModel model, int cost) {
        super();
        this.model = model;
        this.cost = cost;

        this.phList= new LinkedList<>();
        this.brightList= new LinkedList<>();
        this.conductList= new LinkedList<>();
        this.tempList= new LinkedList<>();
    }


    public void addPhValue(double value ){
        this.phList.add(0,value);
    }

    @Override
    public PlantModel getModel() {
        return model;
    }


    public int getCost() {
        return cost;
    }

    public List<Double> getPhList() {
        return phList;
    }

    public List<Double> getBrightList() {
        return brightList;
    }

    public List<Double> getConductList() {
        return conductList;
    }

    public List<Double> getTempList() {
        return tempList;
    }

    public void addBrightValue(double value ){
        this.brightList.add(0,value);
    }

    public void addConductValue(double value ){
        this.conductList.add(0,value);
    }

    public void addTempValue(double value ){
        this.tempList.add(0,value);
    }


    @Override
    public String toString() {
        return "PlantImpl [model=" + model + ", cost=" + cost + ", phList=" + phList + ", brightList=" + brightList
                + ", conductList=" + conductList + ", tempList=" + tempList + "]";
    }




}
