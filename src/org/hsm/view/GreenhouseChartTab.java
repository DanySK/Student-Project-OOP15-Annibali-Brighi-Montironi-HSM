package org.hsm.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *This tab is used to show charts about the greenhouse. 
 *
 */
public class GreenhouseChartTab implements GUIComponent {

    private final JPanel panel;

    /**
     * Create the tab for greenhouse composition charts.
     */
    public GreenhouseChartTab() {
        this.panel = new JPanel(new BorderLayout());

        final JPanel buttonPanel = new JPanel();
        final JButton numPlantChart = new JButton("Show Greenhouse Composition by Number");
        final JButton spacePlantChart = new JButton("Show Greenhouse Composition by Occupied Space");
        buttonPanel.add(numPlantChart);
        buttonPanel.add(spacePlantChart);

        final JPanel chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createEtchedBorder());

        this.panel.add(buttonPanel, BorderLayout.NORTH);
        this.panel.add(chartPanel, BorderLayout.CENTER);

    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
