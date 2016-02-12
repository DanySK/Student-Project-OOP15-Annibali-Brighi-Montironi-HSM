package org.hsm.view.utility;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

/**
 *My GUI Factory implementation for Hsm.
 *
 */
public class MyGUIFactory implements GUIFactory {

    private static final double SIZE_LABEL_FACTOR = 1.3;
    private static final double SIZE_FIELD_FACTOR = 1.3;

    @Override
    public JButton createButton(final String name, final Icon image) {
        final JButton button = new JButton();
        button.setText(name);
        button.setIcon(image);
        return button;
    }

    @Override
    public JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text);
        final Font font = label.getFont();
        final Font newFont = new Font("newFont", font.getStyle(), (int) (font.getSize() * SIZE_LABEL_FACTOR));
        label.setFont(newFont);
        return label;
    }

    @Override
    public JSpinner createSpinner(final int size, final SpinnerModel model) {
        final JSpinner spin = new JSpinner(model);
        final Component mySpinnerEditor = spin.getEditor();
        final JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(size);
        return spin;
    }

    @Override
    public JTextField createTextField(final int size) {
        final JTextField field = new JTextField(size);
        field.setHorizontalAlignment(JTextField.RIGHT);
        final Font font = field.getFont();
        final Font newFont = new Font("newFont", font.getStyle(), (int) (font.getSize() * SIZE_FIELD_FACTOR));
        field.setFont(newFont);
        return field;
    }

    @Override
    public JTable createTable(final Object... columns) {
        final TableModel model = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 8517517831747874057L;
            @Override
            public boolean isCellEditable(final int rowIndex, final int mColIndex) {
                return false;
            }
        };
        final JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setAutoscrolls(true);
        table.setFillsViewportHeight(true);
        return table;
    }

    @Override
    public JFreeChart createPieChart(final DefaultPieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
        final LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
        legend.setPosition(RectangleEdge.LEFT);
        return chart;
    }
}
