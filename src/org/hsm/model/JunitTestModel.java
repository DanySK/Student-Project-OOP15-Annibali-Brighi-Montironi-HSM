package org.hsm.model;

import static org.junit.Assert.*;

import org.hsm.model.db.Database;
import org.hsm.model.db.DatabaseImpl;
import org.junit.Test;

public class JunitTestModel {

    @Test
    public void test() {
        final Database db = new DatabaseImpl();
        db.addPlantModel("pianta_uno", "botanical_uno", 1, 1, 1, 1, 1, 1, 1);
        db.addPlantModel("pianta_due", "botanical_due", 2, 2, 2, 2, 2, 2, 2);
        db.addPlantModel("pianta_tre", "botanical_tre", 3, 3, 3, 3, 3, 3, 3);
        assertTrue(db.getDb().size() == 3);

        db.removePlantModel("botanical_tre");
        assertTrue(db.getDb().size() == 2);

        final Greenhouse gh = new GreenhouseImpl("myGreenHouse", 100, 1000, GreenHouseType.LINEAR);
        gh.addPlant(db.getPlantModel("botanical_uno"), 10);
        assertTrue(gh.getPlants().size() == 1);

        gh.delPlants(db.getPlantModel("botanical_uno"));
        assertTrue(gh.getPlants().size() == 0);

        for(int i=0;i<100;i++){
            gh.addPlant(db.getPlantModel("botanical_due"), 2);
        }
        
        assertTrue(gh.getPlants().size() == 100);
        
        for(int i=0;i<100;i++){
            gh.addPlant(db.getPlantModel("botanical_uno"), 2);
        }
        
        assertTrue(gh.getPlants().size() == 200);
        
        gh.delPlants(db.getPlantModel("botanical_due"));
        
        assertTrue(gh.getPlants().size() == 100);
        
        
        
    }

}
