package org.hsm.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The dialog that contains the bar chart to compare the plant with the model.
 *
 */
public class BarChartDialog implements VisibleComponent {

    private static final String OPTIMAL = "Optimal Value";
    private static final String CURRENT = "Current Value";
    private static final double BAR_WIDTH_FACTOR = 0.1;
    private final JDialog dialog;


    /**
     * Create the bar chart for comparing values.
     * @param characteristic the name of the characteristic to compare
     * @param unitsOfMeasure the unit of measure to use
     * @param optimalValue the optimal value
     * @param currentValue the current value
     */
    public BarChartDialog(final String characteristic, final String unitsOfMeasure, final double optimalValue, final double currentValue) {
        this.dialog = new JDialog();
        this.dialog.setTitle(characteristic + " Chart");
        this.dialog.setLayout(new BorderLayout());
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(optimalValue, OPTIMAL, characteristic);
        dataset.addValue(currentValue, CURRENT, characteristic);
        final JFreeChart chart = ChartFactory.createBarChart(characteristic + " Comparing Chart", "", unitsOfMeasure, dataset, PlotOrientation.VERTICAL, 
                                 true, true, false);
        final ChartPanel panel = new ChartPanel(chart, false);
        final CategoryPlot categoryPlot = chart.getCategoryPlot();
        final BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        br.setMaxBarWidth(BAR_WIDTH_FACTOR);
        final JButton exit = new JButton("Exit");
        exit.addActionListener(e -> this.dialog.dispose());
        this.dialog.add(panel, BorderLayout.CENTER);
        this.dialog.add(exit, BorderLayout.SOUTH);
    }

    @Override
    public void start() {
        this.dialog.pack();
        this.dialog.setLocationByPlatform(true);
        this.dialog.setVisible(true); 
    }

}
