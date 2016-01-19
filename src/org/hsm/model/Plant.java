package org.hsm.model;

import java.util.List;

public interface Plant {

    
    PlantModel getModel();
    
    int getCost();

    List<Double> getPhList();
    
    List<Double> getBrightList();

    List<Double> getConductList();

    List<Double> getTempList();
    
    void addBrightValue(final double value);

    void addConductValue(final double value);

    void addTempValue(final double value);
    
    void addPhValue(final double value);
}
