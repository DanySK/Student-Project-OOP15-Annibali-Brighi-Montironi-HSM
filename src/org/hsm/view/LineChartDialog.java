package org.hsm.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The dialog that contains the line chart to see the trend of values.
 *
 */
public class LineChartDialog implements VisibleComponent {

    private final JDialog dialog;

    /**
     * Create the line chart to see the value trend.
     * @param characteristic the name of the characteristic
     * @param unitsOfMeasure the unit of measure to use
     * @param valueList the list of values
     */
    public LineChartDialog(final String characteristic, final String unitsOfMeasure, final List<Number> valueList) {
        this.dialog = new JDialog();
        this.dialog.setTitle(characteristic + " Chart");
        this.dialog.setLayout(new BorderLayout());
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < valueList.size(); ++i) {
            dataset.addValue(valueList.get(i), "Value", i);
        }
        final JFreeChart chart = ChartFactory.createLineChart(characteristic + " Line Chart", 
                                                              "Survey Period",
                                                              unitsOfMeasure, 
                                                              dataset, 
                                                              PlotOrientation.VERTICAL, 
                                                              false, 
                                                              true, 
                                                              false);
        final ChartPanel panel = new ChartPanel(chart, false);
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
