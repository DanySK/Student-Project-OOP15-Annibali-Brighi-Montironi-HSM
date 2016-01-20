package org.hsm.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *This tab contains all the information about the current greenhouse.
 *
 */
public class GreenhouseTab implements GUIComponent {

    private static final int INSET = 14;
    private static final int MINIMUM_X_SIZE = 200;
    private static final int MINIMUM_Y_SIZE = 50;
    private final JSplitPane split;

    /**
     *Create the tab for the greenhouse.
     */
    public GreenhouseTab() {
        final GUIFactory factory = new MyGUIFactory();

        //creazione pannello con info greenhouse (INFO PANEL)
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

        //creazione pannello grafici
        final JPanel graphicPanel = new JPanel();
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Used Space", MINIMUM_Y_SIZE);
        dataset.setValue("Free Space", MINIMUM_Y_SIZE);
        final JFreeChart chart = ChartFactory.createPieChart("Greenhouse Space Chart", dataset, true, true, false);
        final ChartPanel p = new ChartPanel(chart);
        graphicPanel.add(p);

        this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, informationPanel, graphicPanel);
        final Dimension minimumSize = new Dimension(MINIMUM_X_SIZE, MINIMUM_Y_SIZE);
        informationPanel.setMinimumSize(minimumSize);
        graphicPanel.setMinimumSize(minimumSize);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);

    }

    @Override
    public JComponent getComponent() {
        return this.split;
    }

}
