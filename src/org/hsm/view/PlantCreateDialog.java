package org.hsm.view;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *The frame which you can add plants in the table.
 *
 */
public class PlantCreateDialog extends AbstractAddDialog {

    private static final int NUM_CHAR = 15;
    private static final int NUM_ROW = PlantCharacteristics.values().length - 1;
    private static final int INSET = 3;
    private static final String DIALOG_TITLE = "Create new Plant";
    private final List<JTextField> fieldList;

    /**
     * Create the add plant dialog.
     * @param frame
     * the main frame of the app
     */
    public PlantCreateDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.fieldList = new ArrayList<>();
        for (int i = 0; i < NUM_ROW; ++i) {
            this.fieldList.add(new JTextField(NUM_CHAR));
        }
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
        this.getJDialog().getContentPane().add(panel);
    }

    @Override
    protected void addAction() {
        // TODO Auto-generated method stub
        for (final JTextField elem: this.fieldList) {
            elem.getText();
        }
    }

}
