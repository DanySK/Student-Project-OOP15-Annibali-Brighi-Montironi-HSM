package org.hsm.view;

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
 *This tab contains all the information about the database of plants.
 *
 */
public class DatabaseTab implements GUIComponent {

    private final JTable table;
    private final JPanel panel;

    /**
     * Create the tab for the plant database.
     * @param frame the main frame of the app
     */
    public DatabaseTab(final JFrame frame) {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        final List<String> columns = IntStream.range(0, PlantModelCharacteristics.values().length)
                .mapToObj(i -> PlantModelCharacteristics.values()[i].getDescription())
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

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        final JButton createPlant = new JButton("Insert new type of plant");
        createPlant.addActionListener(e -> new PlantCreateDialog(frame).start());
        southPanel.add(createPlant);
        southPanel.setSize(southPanel.getPreferredSize());

        final JScrollPane scrollPane = new JScrollPane(this.table);
        this.panel.add(scrollPane);
        this.panel.add(southPanel);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

    /**
     * dscc.
     * @return dscds
     */
    public JTable getTable() {
        return this.table;
    }

}
