package org.hsm.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        this.table = new MyGUIFactory().createTable(PlantModelCharacteristics.getNameList().toArray());
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        final JButton createPlant = new JButton("Insert new type of plant");
        createPlant.addActionListener(e -> new PlantCreateDialog(frame).start());
        final JButton removePlant = new JButton("Remove selected plant");
        removePlant.addActionListener(e -> {
            if (this.table.getSelectedRow() == -1) {
                Messages.errorMessage(frame, "No plant is selected!");
            } else {
                System.out.println("Implenta nel controller");
                //Controller delete model from database
            }
        });
        southPanel.add(createPlant);
        southPanel.add(removePlant);
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
     *Insert the plant into tha table.
     *@param params all the parameters of the plant
     */
    public void insertPlant(final Object... params) {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(params);
    }

    /**
     *Remove the first selected row in the database table.
     */
    public void removeSelectedPlant() {
        final DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.removeRow(this.table.getSelectedRow());
    }

}
