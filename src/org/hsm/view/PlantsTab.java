package org.hsm.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *This tab contains all the information about the plants inside the current greenhouse.
 *
 */
public class PlantsTab implements GUIComponent {

    private static final int FILTER_TXT_SIZE = 50;
    private final JTable table;
    private final JPanel panel;

    /**
     * Create the tab for the plants inside the current greenhouse.
     * @param frame the main frame of the app
     */
    public PlantsTab(final JFrame frame) {

        this.table = new MyGUIFactory().createTable(PlantCharacteristics.getNameList().toArray());
        //pannello con buttoni per azioni su piante
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        final JButton add = new JButton("Add Plant");
        add.addActionListener(e -> {
            new PlantAddDialog(frame);
        });
        final JLabel filterLabel = new JLabel("Find:");
        final JTextField filterField = new JTextField(FILTER_TXT_SIZE);
        final JButton findButton = new JButton("Filter");
        final JButton exitFilter = new JButton("Exit");
        filterField.setMaximumSize(new Dimension(filterField.getPreferredSize().width, filterField.getPreferredSize().height));

        /*set filter option*/
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());
        table.setRowSorter(sorter);
        findButton.addActionListener(e -> {
            final String text = filterField.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
              } else {
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
        });
        exitFilter.addActionListener(e -> {
            sorter.setRowFilter(null);
        });

        southPanel.add(filterLabel);
        southPanel.add(filterField);
        southPanel.add(findButton);
        southPanel.add(exitFilter);
        southPanel.add(Box.createHorizontalGlue());
        final JButton remove = new JButton("Remove Plant");
        remove.addActionListener(e -> {
            if (this.table.getSelectedRow() == -1) {
                Messages.errorMessage(frame, "No plant is selected");
            }
        });
        final JButton updateValues = new JButton("Update Plant Values");
        final JButton addPlants = new JButton("Add Plants");
        southPanel.add(add);
        southPanel.add(addPlants);
        southPanel.add(remove);
        southPanel.add(updateValues);
        southPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        final JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);
        panel.add(southPanel);
    }

    /**
     * Insert row in the table.
     * @param row
     * The row to insert.
     */
    public void insertRow(final Object... row) {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(row);
    }

    /**
     *Remove the selected row into the table.
     */
    public void removeSelectedRow() {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.removeRow(this.table.getSelectedRow());
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
