package org.hsm.view.chart;

import java.awt.BorderLayout;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
     * @param tradList the list of values in a traditional culture
     */
    public LineChartDialog(final String characteristic, final String unitsOfMeasure, final List<Double> valueList,
                           final List<Double> tradList) {
        super(characteristic);
        //chart
        final XYSeries tradSeries = new XYSeries("Traditional Culture");
        final XYSeries currentSeries = new XYSeries("Current");
        final XYSeriesCollection dataset = new XYSeriesCollection(tradSeries);
        dataset.addSeries(currentSeries);
        for (int i = 0; i < valueList.size(); ++i) {
            currentSeries.add(i, valueList.get(i));
            tradSeries.add(i, tradList.get(i));
        }
        final JFreeChart chart = ChartFactory.createXYLineChart(characteristic + " Line Chart", 
                                                              "Survey Period",
                                                              unitsOfMeasure, 
                                                              dataset, 
                                                              PlotOrientation.VERTICAL, 
                                                              true, 
                                                              true, 
                                                              false);
        final ChartPanel panel = new ChartPanel(chart, false);
        super.getJDialog().add(panel, BorderLayout.CENTER);
    }

}
