package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.PlantModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The dialog that contains the bar chart to compare the plant with the model.
 *
 */
public class BarChartDialog implements VisibleComponent {

    private static final String DIALOG_TITLE = "Bar Chart";
    private static final String OPTIMAL = "Optimal Value";
    private static final String CURRENT = "Current Value";
    private final JDialog dialog;

    /**
     * Create the chart.
     * @param frame the frame covered by the chart
     * @param id the id of the plant
     */
    public BarChartDialog(final JFrame frame, final int id) {
        this.dialog = new JDialog(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.dialog.setLayout(new BorderLayout());
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        final PlantModel plant = ControllerImpl.getController().getGreenhouse().getPlants().get(id).getModel();
        dataset.addValue(plant.getBrightness(), OPTIMAL, "Brightness");
        dataset.addValue(2, CURRENT, "Brightness");
        dataset.addValue(plant.getConductivity(), OPTIMAL, "Conductivity");
        dataset.addValue(3, CURRENT, "Conductivity");
        dataset.addValue(plant.getOptimalTemperature(), OPTIMAL, "Temperature");
        dataset.addValue(4, CURRENT, "Temperature");
        dataset.addValue(plant.getPH(), OPTIMAL, "PH");
        dataset.addValue(2, CURRENT, "PH");
        final JFreeChart chart = ChartFactory.createBarChart("Comparing Chart", "Characteristic", "Value", dataset, PlotOrientation.VERTICAL, 
                                 true, true, false);
        final ChartPanel panel = new ChartPanel(chart, false);
        this.dialog.add(panel);
    }

    @Override
    public void start() {
        this.dialog.pack();
        this.dialog.setVisible(true); 
    }

}
