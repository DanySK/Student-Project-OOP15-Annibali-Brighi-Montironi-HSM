package org.hsm.view.tab;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Observable;

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

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.PlantAddDialog;
import org.hsm.view.enumeration.PlantCharacteristics;
import org.hsm.view.gui.GUIComponent;
import org.hsm.view.utility.MyGUIFactory;
import org.hsm.view.utility.Utilities;

/**
 *This tab contains all the information about the plants inside the current greenhouse.
 *
 */
public class PlantsTab extends Observable implements GUIComponent, UpgradeableTable<Integer> {

    private static final int FILTER_TXT_SIZE = 50;
    private final JTable table;
    private final JPanel panel;

    /**
     * Create the tab for the plants inside the current greenhouse.
     * @param frame the main frame of the app
     */
    public PlantsTab(final JFrame frame) {
        //table
        this.table = new MyGUIFactory().createTable(PlantCharacteristics.getNameList().toArray());
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        //buttons
        final JButton remove = new JButton("Remove Plant");
        remove.addActionListener(e -> ControllerImpl.getController().delPlant());
        final JButton removeType = new JButton("Remove Type");
        removeType.addActionListener(e -> ControllerImpl.getController().delPLants());
        final JButton updateValues = new JButton("Update Plant Values");
        updateValues.addActionListener(e -> {
            this.table.setRowSelectionAllowed(false);
            ControllerImpl.getController().autoUpdate(1);
        });
        final JButton stop = new JButton("Stop Updating");
        stop.addActionListener(e -> {
            ControllerImpl.getController().stopUpdate();
            this.table.setRowSelectionAllowed(true);
        });
        final JButton add = new JButton("Add Plant");
        add.addActionListener(e -> {
            if (ControllerImpl.getController().isDbEmpty()) {
                Utilities.errorMessage(frame, "The Database is empty");
            } else {
                new PlantAddDialog(frame).start();
            }
        });
        final JLabel filterLabel = new JLabel("Find:");
        final JTextField filterField = new JTextField(FILTER_TXT_SIZE);
        final JButton findButton = new JButton("Filter");
        filterField.setMaximumSize(new Dimension(filterField.getPreferredSize().width, filterField.getPreferredSize().height));
        //filter
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());
        this.table.setRowSorter(sorter);
        findButton.addActionListener(e -> {
            final String text = filterField.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
              } else {
                sorter.setRowFilter(RowFilter.regexFilter(text));
              }
        });
        southPanel.add(filterLabel);
        southPanel.add(filterField);
        southPanel.add(findButton);
        southPanel.add(Box.createHorizontalGlue());
        southPanel.add(add);
        southPanel.add(remove);
        southPanel.add(removeType);
        southPanel.add(updateValues);
        southPanel.add(stop);
        southPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        final JScrollPane scrollPane = new JScrollPane(table);
        this.panel.add(scrollPane);
        this.panel.add(southPanel);
    }

    @Override
    public Integer getSelectedRowIdentifier() throws IllegalStateException {
        if (this.table.getSelectedRow() == -1) {
            throw new IllegalStateException();
        }
        final int selectedRowIndex = this.table.getSelectedRow();
        final int modelRow = this.table.convertRowIndexToModel(selectedRowIndex);
        return (int) this.table.getModel().getValueAt(modelRow, PlantCharacteristics.ID.ordinal());
    }

    @Override
    public void insertRow(final Object... row) {
        this.addRow(row);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void removeSelectedRow() {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        final int row = this.table.getSelectedRow();
        final int modelRow = this.table.convertRowIndexToModel(row);
        model.removeRow(modelRow);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void updateRow(final Object... row) {
        this.addRow(row);
    }

    private void addRow(final Object... row) {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(row);
    }

    @Override
    public void clean() {
        final DefaultTableModel dm = (DefaultTableModel) table.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
