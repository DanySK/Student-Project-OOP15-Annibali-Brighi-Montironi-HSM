package org.hsm.model;

import static org.junit.Assert.*;

import org.hsm.model.db.Database;
import org.hsm.model.db.DatabaseImpl;
import org.junit.Test;

public class JunitTestModel {

    @Test
    public void test() {
        //create new database
        final Database db = new DatabaseImpl();
        //add 3 type of plants
        db.addPlantModel("pianta_uno", "botanical_uno", 1, 1, 1, 1, 60, 1, 1);
        db.addPlantModel("pianta_due", "botanical_due", 2, 2, 2, 2, 30, 2, 2);
        db.addPlantModel("pianta_tre", "botanical_tre", 3, 3, 3, 3, 30, 3, 3);
        assertTrue(db.getDb().size() == 3);

        //remove plant "botanical_tre"
        db.removePlantModel("botanical_tre");
        assertTrue(db.getDb().size() == 2);

        //create new greenhouse and add a plant (botanical_uno)
        final Greenhouse gh = new GreenhouseImpl("myGreenHouse", 1, 1000, GreenHouseType.LINEAR);
        assertTrue(gh.getFreeSize() == gh.getSize());
        int id = gh.addPlant(db.getPlantModel("botanical_uno"), 10);
        assertTrue(gh.getPlants().size() == 1);

       
        //remove a plant
        gh.delPlant(id);
        assertTrue(gh.getPlants().size() == 0);

        //add 100 plants of botanical_due
        for(int i=0;i<100;i++){
            gh.addPlant(db.getPlantModel("botanical_due"), 2);
        }
        assertTrue(gh.getPlants().size() == 100);
        
        //add 100 plants of botanical_uno
        for(int i=0;i<100;i++){
            gh.addPlant(db.getPlantModel("botanical_uno"), 2);
        }
        assertTrue(gh.getPlants().size() == 200);
        
        //remove all botanical_due
        gh.delPlants(db.getPlantModel("botanical_due"));
        assertTrue(gh.getPlants().size() == 100);
        try{
            gh.addPlant(db.getPlantModel("botanical_tre"), 20);
        } catch (NullPointerException e){
            System.out.println("error: plant don't exist");
        } catch (IllegalStateException i){
            System.out.println("error: not enough free space");
        }
        
        System.out.println(gh.getFreeSize());
        
    }

}
