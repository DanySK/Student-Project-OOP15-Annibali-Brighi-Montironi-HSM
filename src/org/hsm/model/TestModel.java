package org.hsm.model;

import org.hsm.model.db.Database;
import org.hsm.model.db.DatabaseImpl;

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

        final Greenhouse gn = new GreenhouseImpl("serra_uno ", 40, 10, GreenHouseType.GRID);

        gn.addPlant(db.getPlantModel("pasticcio"), 12);

        System.out.println(gn);

    }
}
