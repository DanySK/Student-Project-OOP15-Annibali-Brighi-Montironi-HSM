package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *The class which create and use the Tabel.
 *
 */
public class Tabbed implements GUIComponent {

    private static final int INSET = 14;
    private static final int MINIMUM_X_SIZE = 200;
    private static final int MINIMUM_Y_SIZE = 50;
    private final JPanel panel;
    private final JTable table;
    private final JTabbedPane informationTabbed;

    /**
     *Create the Tabel.
     */
    public Tabbed() {
        this.informationTabbed = new JTabbedPane();
        //this.informationTabbed.setVisible(false);
        this.panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        final List<String> columns = IntStream.range(0, PlantCharacteristics.values().length)
                .mapToObj(x -> PlantCharacteristics.values()[x].getDescription())
                .collect(Collectors.toList());
        final TableModel model = new DefaultTableModel(columns.toArray(), 0) {
            private static final long serialVersionUID = 8517517831747874057L;
            public boolean isCellEditable(final int rowIndex, final int mColIndex) {
                return false;
            }
        };
        this.table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        final GUIFactory factory = new MyGUIFactory();
        final JPanel panelGreenhouse = new JPanel(new BorderLayout());

        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Used Space", MINIMUM_Y_SIZE);
        dataset.setValue("Free Space", MINIMUM_Y_SIZE);
        final JFreeChart chart = ChartFactory.createPieChart("Greenhouse Space Chart", dataset, true, true, false);
        final ChartPanel p = new ChartPanel(chart);
        panelGreenhouse.add(p);

        final JPanel informationPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel nameLabel = factory.createLabel("Name : ");
        informationPanel.add(nameLabel, gbc);
        ++gbc.gridx;
        final JLabel name = factory.createLabel("");
        informationPanel.add(name, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        final JLabel dimensionLabel = factory.createLabel("Dimension : ");
        informationPanel.add(dimensionLabel, gbc);
        ++gbc.gridx;
        final JLabel dimension = factory.createLabel("                     ");
        informationPanel.add(dimension, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        final JLabel numPlantsLabel = factory.createLabel("Number of Plants : ");
        informationPanel.add(numPlantsLabel, gbc);
        ++gbc.gridx;
        final JLabel plantsLabel = factory.createLabel("                     ");
        informationPanel.add(plantsLabel, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        final JLabel spaceOccupiedLabel = factory.createLabel("Space Occupied : ");
        informationPanel.add(spaceOccupiedLabel, gbc);
        ++gbc.gridx;
        final JLabel occupiedSpace = factory.createLabel("                     ");
        informationPanel.add(occupiedSpace, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        final JLabel labelTypology = factory.createLabel("Typology : ");
        informationPanel.add(labelTypology, gbc);
        ++gbc.gridx;
        final JLabel typology = new JLabel("", new ImageIcon("res/linear.jpg"), JLabel.CENTER);
        informationPanel.add(typology, gbc);

        final JScrollPane scrollPane = new JScrollPane(table);
        final JPanel panelPlants = new JPanel(new BorderLayout());
        panelPlants.add(scrollPane, BorderLayout.CENTER);
        final ImageIcon image = new ImageIcon("res/plant.png");
        final JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, informationPanel, panelGreenhouse);
        final Dimension minimumSize = new Dimension(MINIMUM_X_SIZE, MINIMUM_Y_SIZE);
        informationPanel.setMinimumSize(minimumSize);
        panelGreenhouse.setMinimumSize(minimumSize);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);

        informationTabbed.add("Greenhouse Information", split);
        informationTabbed.add("Plants Database", panelPlants);
        informationTabbed.add("Graphic", new JLabel(image));
        this.panel.add(informationTabbed, BorderLayout.CENTER);
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

    /**
     *Remove the selected row into the table.
     */
    public void removeSelectedRow() {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.removeRow(this.table.getSelectedRow());
    }

    /**
     *Set the tabbed visible and usable.
     */
    public void activeTabbed() {
        this.informationTabbed.setVisible(true);
    }

    /**
     *Set the tabbed disabled and unusable.
     */
    public void disableTabbed() {
        this.informationTabbed.setVisible(false);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
