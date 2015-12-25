package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *The class which create and use the Tabel.
 *
 */
public class Tabel implements GUIComponent {

    private static final Object[] COLUMN_LIST = new Object[]{"Nome", "Nome Latino", "ph", "Luminosità", "Temperatura"};
    private final JPanel panel;
    private final JTable table;

    /**
     *Create the Tabel.
     */
    public Tabel() {
        final JTabbedPane tabbed = new JTabbedPane();
        this.panel = new JPanel(new BorderLayout());
        this.table = new JTable(new DefaultTableModel(COLUMN_LIST, 0));
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        final JScrollPane scrollPane = new JScrollPane(table);
        final JPanel panelPlants = new JPanel(new BorderLayout());
        final JPanel panelPlantSouth = new JPanel(new FlowLayout());
        panelPlants.add(scrollPane, BorderLayout.CENTER);
        final JPanel panelPlantNorth = new JPanel(new BorderLayout());
        panelPlantNorth.add(scrollPane, BorderLayout.CENTER);
        panelPlants.add(panelPlantNorth, BorderLayout.CENTER);
        panelPlants.add(panelPlantSouth, BorderLayout.PAGE_END);
        tabbed.add("Plants", panelPlants);
        final ImageIcon image = new ImageIcon("res/plant.png");
        tabbed.add("Graphic", new JLabel(image));
        this.panel.add(tabbed, BorderLayout.CENTER);
    }

    /**
     * Insert row in the table.
     * @param row
     * The row to insert.
     */
    public void insertRow(final Object... row) {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(row);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
