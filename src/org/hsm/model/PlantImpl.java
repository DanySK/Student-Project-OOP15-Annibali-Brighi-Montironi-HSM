package org.hsm.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 *
 */
public class PlantImpl implements Plant, Serializable {

    private static final long serialVersionUID = 1101353617623045838L;

    private final PlantModel model;
    private final int cost;
    private final List<Double> phList;
    private final List<Double> brightList;
    private final List<Double> conductList;
    private final List<Double> tempList;

    /**
     *
     * @param model
     *            PlantModel
     * @param cost
     *            Cost in euro
     */
    public PlantImpl(final PlantModel model, final int cost) {
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
        this.phList.add(value);
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
        this.brightList.add(value);
    }

    @Override
    public void addConductValue(final double value) {
        this.conductList.add(value);
    }

    @Override
    public void addTempValue(final double value) {
        this.tempList.add(value);
    }

    @Override
    public String toString() {
        return "PlantImpl [model=" + model + ", cost=" + cost + ", phList=" + phList + ", brightList=" + brightList
                + ", conductList=" + conductList + ", tempList=" + tempList + "]";
    }

    @Override
    public double getLastBrightValue() {
        if (this.brightList.isEmpty()){
            return 0;
        }
        return this.brightList.get(this.brightList.size() -1);
    }

    @Override
    public double getLastConductValue() {
        if (this.brightList.isEmpty()){
            return 0;
        }
        return this.conductList.get(this.conductList.size() -1);
    }

    @Override
    public double getLastTempValue() {
        if (this.tempList.isEmpty()){
            return 0;
        }
        return this.tempList.get(this.tempList.size() -1);
    }

    @Override
    public double getLastPhValue() {
        if (this.phList.isEmpty()){
            return 0;
        }
        return this.phList.get(this.phList.size() -1);
    }

}
