package org.hsm.model;

public class TestModel {

    public static void main(String[] args) {

        final Database db = new DBplants();
        db.addPlantModel("ciccio", "pasticcio", 1, 2, 3, 4, 5, 6, 7);

        final GreenHouse gn = new GreenhouseImp();

        gn.addPlants(5, new PlantImpl(db.getPlantModel("pasticcio"),12));



        System.out.println(gn);



    }
}
