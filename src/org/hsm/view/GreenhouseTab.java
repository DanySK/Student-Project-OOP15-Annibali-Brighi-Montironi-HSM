package org.hsm.view;

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
import org.hsm.model.GreenHouse;
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
     */
    public void setGreenhouse() {
        final GreenHouse green = ControllerImpl.getController().getGreenhouse();
        this.fieldMap.get(GreenhouseCharacteristics.NAME).setText(green.getName());
        this.fieldMap.get(GreenhouseCharacteristics.DIMENSION).setText(Double.toString(green.getSize()));
        this.fieldMap.get(GreenhouseCharacteristics.COST).setText(Double.toString(green.getCost()));
        this.fieldMap.get(GreenhouseCharacteristics.TYPOLOGY).setText(green.getType().toString());
        this.fieldMap.get(GreenhouseCharacteristics.FREE_SPACE).setText(Double.toString(green.getFreeSize()));
        this.fieldMap.get(GreenhouseCharacteristics.USED_SPACE).setText(Double.toString(green.getOccSize()));
        this.fieldMap.get(GreenhouseCharacteristics.NUMBER_OF_PLANTS).setText(Integer.toString(green.getNumberOfPlants()));
    }

    @Override
    public void update(final Observable arg0, final Object arg1) {
        final GreenHouse green = ControllerImpl.getController().getGreenhouse();
        this.fieldMap.get(GreenhouseCharacteristics.FREE_SPACE).setText(Double.toString(green.getFreeSize()));
        this.fieldMap.get(GreenhouseCharacteristics.USED_SPACE).setText(Double.toString(green.getOccSize()));
        this.fieldMap.get(GreenhouseCharacteristics.NUMBER_OF_PLANTS).setText(Integer.toString(green.getNumberOfPlants()));
        this.dataSet.setValue(OCCUPIED_SPACE, green.getOccSize());
        this.dataSet.setValue(FREE_SPACE, green.getFreeSize());
    }

    @Override
    public JComponent getComponent() {
        return this.split;
    }

}
