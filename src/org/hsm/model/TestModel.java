package org.hsm.model;

/**
 *
 *
 *
 */
public final class TestModel {

    private TestModel() {
    }

    /**
     *
     * @param args
     *            ...
     */
    // CHECKSTYLE:OFF: checkstyle:magicnumber
    public static void main(final String[] args) {

        final Database db = new DatabaseImpl();
        db.addPlantModel("ciccio", "pasticcio", 1, 2, 3, 4, 5, 6, 7);

        final GreenHouse gn = new GreenhouseImp("serra_uno ", 40, 10, GreenHouseType.GRID);

        gn.addPlant(db.getPlantModel("pasticcio"), 12);

        System.out.println(gn);

    }
}
