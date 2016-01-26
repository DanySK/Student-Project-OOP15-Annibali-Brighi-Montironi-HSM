package org.hsm.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

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
public class GreenhouseTab implements GUIComponent {

    private static final int INSET_Y = 12;
    private static final int INSET_X = 7;
    private static final int TXT_FIELD_SIZE = 20;
    private static final int MINIMUM_X_SIZE = 355;
    private static final int MINIMUM_Y_SIZE = 300;
    private final JSplitPane split;
    private final Map<GreenhouseCharacteristics, JTextField> fieldMap;

    /**
     *Create the tab for the greenhouse.
     */
    public GreenhouseTab() {
        final GUIFactory factory = new MyGUIFactory();
        this.fieldMap = new HashMap<>();
        //creazione pannello con info greenhouse (INFO PANEL)
        final JPanel detailsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_Y, 0, INSET_Y, INSET_X);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (final GreenhouseCharacteristics elem : GreenhouseCharacteristics.values()) {
            gbc.anchor = GridBagConstraints.WEST;
            detailsPanel.add(factory.createLabel(elem.getDescription()), gbc);
            ++gbc.gridx;
            final JTextField field = factory.createTextField(TXT_FIELD_SIZE);
            field.setEditable(false);
            this.fieldMap.put(elem, field);
            gbc.anchor = GridBagConstraints.EAST;
            detailsPanel.add(field, gbc);
            gbc.gridx = 0;
            ++gbc.gridy;
        }

        //creazione pannello grafici
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Used Space", MINIMUM_Y_SIZE);
        dataset.setValue("Free Space", MINIMUM_Y_SIZE);
        final JFreeChart chart = ChartFactory.createPieChart("Greenhouse Space Chart", dataset, true, true, false);
        final ChartPanel graphicPanel = new ChartPanel(chart);

        this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, detailsPanel, graphicPanel);
        final Dimension minimumSize = new Dimension(MINIMUM_X_SIZE, 0);
        detailsPanel.setMinimumSize(minimumSize);
        graphicPanel.setMinimumSize(minimumSize);
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
        this.fieldMap.get(GreenhouseCharacteristics.FREE_SPACE).setText(Double.toString(green.getFreeSize()));
        this.fieldMap.get(GreenhouseCharacteristics.COST).setText(Double.toString(green.getCost()));
    }

    @Override
    public JComponent getComponent() {
        //inserisco dati a caso
        for (final JTextField elem: this.fieldMap.values()) {
            elem.setText("dati a caso");
        }
        return this.split;
    }

}
