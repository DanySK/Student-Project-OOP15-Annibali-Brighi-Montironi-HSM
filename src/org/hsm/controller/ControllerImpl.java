package org.hsm.controller;

import org.hsm.model.GreenHouseType;
import org.hsm.model.Plant;

/**
 * Implementation of Controller Interface.
 *
 */
public class ControllerImpl implements Controller {

    /**
     * Singleton istance of controller.
     */
    private static final ControllerImpl CONTROLLER_IMPL = new ControllerImpl();

    /**
     * 
     * @return the istance of controller
     */
    public static ControllerImpl getController() {
        return CONTROLLER_IMPL;
    }

    @Override
    public void crateGreenhouse(final String name, final GreenHouseType greenhouseType) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteGreenhouse() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addPlants(final int nPlants, final Plant plant) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delPlant(final int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delPLants(final Plant plant) {
        // TODO Auto-generated method stub
    }

    @Override
    public void createNewPlant(final String name, final String botanicalName, final int ph, final int brightness, final int conductibility,
            final int optimalGrowthTime, final int temperature, final int life, final int size, final int cost) {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveGreenhouse() {
        // TODO Auto-generated method stub
    }

    @Override
    public void loadGreenhouse() {
        // TODO Auto-generated method stub
    }

}
