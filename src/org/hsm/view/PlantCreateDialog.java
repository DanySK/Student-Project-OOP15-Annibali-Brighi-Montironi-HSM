package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *The frame which you can add plants in the table.
 *
 */
public class PlantCreateDialog {

    private static final int NUM_CHAR = 15;
    private static final int NUM_ROW = PlantCharacteristics.values().length - 1;
    private static final int INSET = 3;
    private static final String TITLE = "Create new Plant";
    private final JDialog dialog;
    private final List<JTextField> fieldList;

    /**
     * Create the add plant dialog.
     * @param frame
     * the main frame of the app
     */
    public PlantCreateDialog(final JFrame frame) {
        this.fieldList = new ArrayList<>();
        for (int i = 0; i < NUM_ROW; ++i) {
            this.fieldList.add(new JTextField(NUM_CHAR));
        }
        this.dialog = new JDialog(frame, TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dialog.setLayout(new BorderLayout());
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        for (int i = 0; i < NUM_ROW; ++i) {
            gbc.gridy = i;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.LINE_END;
            panel.add(new JLabel(PlantCharacteristics.values()[i + 1].getDescription()), gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            panel.add(this.fieldList.get(i), gbc);
        }
        this.dialog.getContentPane().add(panel);
        final JPanel southPanel = new JPanel(new FlowLayout());
        final JButton add = new JButton("Add");
        southPanel.add(add);
        add.addActionListener(e -> createPlant());
        this.dialog.getContentPane().add(southPanel, BorderLayout.PAGE_END);
    }

    private void createPlant() {
        final List<String> list = new ArrayList<>();
        for (final JTextField field: this.fieldList) {
            list.add(field.getText());
        }
        //Controller.createNewPlant(list);
    }

    /**
     *Set the dialog visible.
     */
    public void start() {
        this.dialog.pack();
        this.dialog.setLocationByPlatform(true);
        this.dialog.setVisible(true);
    }

}
