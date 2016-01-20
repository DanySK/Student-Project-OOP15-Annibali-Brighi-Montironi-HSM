package org.hsm.view;

import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *This tab contains all the information about the plants inside the current greenhouse.
 *
 */
public class PlantsTab implements GUIComponent {

    private final JTable table;
    private final JPanel panel;

    /**
     * Create the tab for the plants inside the current greenhouse.
     * @param frame the main frame of the app
     */
    public PlantsTab(final JFrame frame) {
        final List<String> columns = IntStream.range(0, PlantCharacteristics.values().length)
                .mapToObj(x -> PlantCharacteristics.values()[x].getDescription())
                .collect(Collectors.toList());
        final TableModel model = new DefaultTableModel(columns.toArray(), 0) {
            private static final long serialVersionUID = 8517517831747874057L;
            @Override
            public boolean isCellEditable(final int rowIndex, final int mColIndex) {
                return false;
            }
        };
        this.table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setAutoscrolls(true);
        table.setFillsViewportHeight(true);

        //pannello con buttoni per azioni su piante
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        final JButton add = new JButton("Add Plant");
        add.addActionListener(e -> {
            new PlantAddDialog(frame).start();
        });
        final JButton remove = new JButton("Remove Plant");
        final JButton createPlant = new JButton("Create new Plant");
        createPlant.addActionListener(e -> {
            new PlantCreateDialog(frame).start();
        });
        final JButton addPlants = new JButton("Add Plants");
        southPanel.add(add);
        southPanel.add(addPlants);
        southPanel.add(remove);
        southPanel.add(createPlant);
        southPanel.setSize(southPanel.getPreferredSize());
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
