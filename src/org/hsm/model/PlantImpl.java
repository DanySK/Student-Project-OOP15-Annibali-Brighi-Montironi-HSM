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
    private static final int CENT_FACTOR = 100;
    private final PlantModel model;
    private final int cost;
    private final List<Double> phList;
    private final List<Double> brightList;
    private final List<Double> conductList;
    private final List<Double> tempList;

    private final List<Double> phListTrad;
    private final List<Double> brightListTrad;
    private final List<Double> conductListTrad;
    private final List<Double> tempListTrad;

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
        
        this.phListTrad = new LinkedList<>();
        this.brightListTrad = new LinkedList<>();
        this.conductListTrad = new LinkedList<>();
        this.tempListTrad = new LinkedList<>();
    }

    @Override
    public void addPhValue(final double value) {
        this.phList.add(value);
    }

    @Override
    public PlantModel getModel() {
        return this.model;
    }

    @Override
    public double getCost() {
        return (double) cost / CENT_FACTOR;
    }

    @Override
    public List<Double> getPhList() {
        return this.phList;
    }

    @Override
    public List<Double> getBrightList() {
        return this.brightList;
    }

    @Override
    public List<Double> getConductList() {
        return this.conductList;
    }

    @Override
    public List<Double> getTempList() {
        return this.tempList;
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

    @Override
    public List<Double> getPhListTraditional() {
        return this.phListTrad;
    }

    @Override
    public List<Double> getBrightListTraditional() {
       return this.brightListTrad;
    }

    @Override
    public List<Double> getConductListTraditional() {
       return this.conductListTrad;
    }

    @Override
    public List<Double> getTempListTraditional() {
        return this.tempListTrad;
    }

    @Override
    public void addBrightValueTraditional(final double value) {
        this.brightListTrad.add(value);
    }

    @Override
    public void addConductValueTraditional(final double value) {
       this.conductListTrad.add(value);
        
    }

    @Override
    public void addTempValueTraditional( final double value) {
       this.tempListTrad.add(value);
    }

    @Override
    public void addPhValueTraditional(double value) {
       this.phListTrad.add(value);
    }

    @Override
    public double getLastBrightValueTraditional() {
        if (this.brightListTrad.isEmpty()){
            return 0;
        }
        return this.brightListTrad.get(this.brightListTrad.size() -1);
    }

    @Override
    public double getLastConductValueTraditional() {
       if (this.conductListTrad.isEmpty()){
           return 0;
       }
       return this.conductListTrad.get(this.conductListTrad.size() -1);
    }

    @Override
    public double getLastTempValueTraditional() {
        if (this.tempListTrad.isEmpty()){
            return 0;
        }
        return this.tempListTrad.get(this.tempListTrad.size() -1);
    }

    @Override
    public double getLastPhValueTraditional() {
        if (this.phListTrad.isEmpty()){
            return 0;
        }
        return this.phListTrad.get(this.phListTrad.size() -1);
    }

    @Override
    public int nUpdate() {
        return this.phList.size();
    }

}
