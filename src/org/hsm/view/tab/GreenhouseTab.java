package org.hsm.view.tab;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.enumeration.GreenhouseCharacteristics;
import org.hsm.view.gui.GUIComponent;
import org.hsm.view.utility.GUIFactory;
import org.hsm.view.utility.MyGUIFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


/**
 *This tab contains all the information about the current greenhouse.
 *
 */
public class GreenhouseTab implements GUIComponent, Observer {

    private static final int INSET_Y = 12;
    private static final int INSET_X = 7;
    private static final int TXT_FIELD_SIZE = 20;
    private static final int MINIMUM_X_SIZE = 355;
    private static final int CENT_FACTOR = 100;
    private static final String DOUBLE_FORMAT = "%.2f";
    private static final String OCCUPIED_SPACE = "Occupied Space";
    private static final String FREE_SPACE = "Free Space";
    private static final String CHART_TITLE = "Greenhouse Space Chart";
    private final JSplitPane split;
    private final Map<GreenhouseCharacteristics, JTextField> fieldMap;
    private final DefaultPieDataset dataSet;

    /**
     *Create the tab for the greenhouse.
     */
    public GreenhouseTab() {
        final GUIFactory factory = new MyGUIFactory();
        this.fieldMap = new HashMap<>();

        final JPanel detailsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_Y, 0, INSET_Y, INSET_X);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (final GreenhouseCharacteristics elem : GreenhouseCharacteristics.values()) {
            gbc.anchor = GridBagConstraints.WEST;
            detailsPanel.add(factory.createLabel(elem.toString()), gbc);
            ++gbc.gridx;
            final JTextField field = factory.createTextField(TXT_FIELD_SIZE);
            field.setEditable(false);
            this.fieldMap.put(elem, field);
            gbc.anchor = GridBagConstraints.EAST;
            detailsPanel.add(field, gbc);
            gbc.gridx = 0;
            ++gbc.gridy;
        }

        this.dataSet = new DefaultPieDataset();
        final JFreeChart chart =  ChartFactory.createPieChart(CHART_TITLE, this.dataSet, true, true, false);
        final ChartPanel chartPanel = new ChartPanel(chart);

        this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, detailsPanel, chartPanel);
        final Dimension minimumSize = new Dimension(MINIMUM_X_SIZE, 0);
        detailsPanel.setMinimumSize(minimumSize);
        chartPanel.setMinimumSize(minimumSize);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);
    }

    /**
     *  Set greenhouse view.
     * @param name the greenhouse name
     * @param size the greenhouse size
     * @param cost the greenhouse cost
     * @param typology the greenhouse typology
     * @param freeSpace the greenhouse free space
     * @param occupiedSpace the greenhouse occupied space
     * @param numberOfPlants the number of plants of the greenhouse
     */
    public void setGreenhouse(final String name, final double size, final double cost, final String typology, 
            final double freeSpace, final double occupiedSpace, final int numberOfPlants) {
        this.fieldMap.get(GreenhouseCharacteristics.NAME).setText(name);
        this.fieldMap.get(GreenhouseCharacteristics.DIMENSION).setText(String.format(DOUBLE_FORMAT, size));
        this.fieldMap.get(GreenhouseCharacteristics.COST).setText(String.format(DOUBLE_FORMAT, cost / CENT_FACTOR));
        this.fieldMap.get(GreenhouseCharacteristics.TYPOLOGY).setText(typology);
        this.fieldMap.get(GreenhouseCharacteristics.FREE_SPACE).setText(String.format(DOUBLE_FORMAT, freeSpace));
        this.fieldMap.get(GreenhouseCharacteristics.USED_SPACE).setText(String.format(DOUBLE_FORMAT, occupiedSpace));
        this.fieldMap.get(GreenhouseCharacteristics.NUMBER_OF_PLANTS).setText(Integer.toString(numberOfPlants));
    }

    @Override
    public void update(final Observable arg0, final Object arg1) {
        this.fieldMap.get(GreenhouseCharacteristics.FREE_SPACE).
            setText(String.format(DOUBLE_FORMAT, ControllerImpl.getController().getGreenhouse().getFreeSize()));
        this.fieldMap.get(GreenhouseCharacteristics.USED_SPACE).
            setText(String.format(DOUBLE_FORMAT, ControllerImpl.getController().getGreenhouse().getOccSize()));
        this.fieldMap.get(GreenhouseCharacteristics.NUMBER_OF_PLANTS)
            .setText(Integer.toString(ControllerImpl.getController().getGreenhouse().getNumberOfPlants()));
        this.dataSet.setValue(OCCUPIED_SPACE, ControllerImpl.getController().getGreenhouse().getOccSize());
        this.dataSet.setValue(FREE_SPACE, ControllerImpl.getController().getGreenhouse().getFreeSize());
    }

    @Override
    public JComponent getComponent() {
        return this.split;
    }

}
