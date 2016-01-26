package org.hsm.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *implementation of the Greenhouse interface.
 */
public class GreenhouseImp implements GreenHouse {

    private final Map<Integer, Plant > plantMap = new HashMap<>();
    private final IDmanager productID = new IDmanager();
    private final String name;

    /**
     * This method add some plants to the Greenhouse.
     * @param n
     *      number of plantsa
     * @param plant
     *      an object of type Plant
     */
    @Override
    public void addPlants(final int n, final PlantModel model, final int cost) {
        for (int i = 0; i < n; i++) {
            this.plantMap.put(productID.getID(), new PlantImpl(model, cost));
        }
    }

    /**
     * Delete plant with the ID provided in input.
     *
     * @param id
     *            Identifier for the plant
     */
    @Override
    public void delPlant(final int id) {
        this.plantMap.remove(id);
    }

    /**
     * Delete all plants of the same type of plant provided in input.
     *
     * @param plant
     *            type of plant to be delete
     */
    @Override
    public void delPlants(final PlantModel plant) {
        final Set<Integer> set = new HashSet<>();
       for (Map.Entry<Integer, Plant> elem : this.plantMap.entrySet()) {
           if (elem.getValue().getModel().equals(plant)) {
              set.add(elem.getKey());
           }
       }
       set.stream().forEach(i -> {
           this.plantMap.remove(i);
       });
    }

    @Override
    public Map<Integer, Plant> getPlants() {
        return plantMap;
    }

   public String getName(){
       return this.name;
   }


}
