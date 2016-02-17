package org.hsm.view.chart;

import java.awt.BorderLayout;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The dialog that contains the line chart to see the trend of values.
 *
 */
public class LineChartDialog extends AbstractChartDialog {

    /**
     * Create the line chart to see the value trend.
     * @param characteristic the name of the characteristic
     * @param unitsOfMeasure the unit of measure to use
     * @param valueList the list of values
     */
    public LineChartDialog(final String characteristic, final String unitsOfMeasure, final List<Double> valueList) {
        super(characteristic);
        //chart
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < valueList.size(); ++i) {
            dataset.addValue(valueList.get(i), "", Integer.toString(i));
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
        super.getJDialog().add(panel, BorderLayout.CENTER);
    }

}
