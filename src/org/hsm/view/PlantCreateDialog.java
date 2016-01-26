package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.hsm.controller.ControllerImpl;

/**
 *The frame which you can add plants in the table.
 *
 */
public class PlantCreateDialog extends AbstractAddDialog {

    private static final int NUM_CHAR = 15;
    private static final int NUM_SPINNER = 8;
    private static final int INSET = 3;
    private static final double DELTA = 0.1;
    private static final String DIALOG_TITLE = "Create new Plant";
    private final JTextField nameField;
    private final JTextField botanicalNameField;
    private final List<JSpinner> spinnerList;

    /**
     * Create the add plant dialog.
     * @param frame
     * the main frame of the app
     */
    public PlantCreateDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.spinnerList = new ArrayList<>();
        final GUIFactory factory = new MyGUIFactory();
        final JPanel superPanel = new JPanel();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.gridx = 0;
        gbc.gridy = 0;
        final PlantModelCharacteristics[] array =  PlantModelCharacteristics.values();
        for (int i = 0; i < array.length; ++i) {
            panel.add(new JLabel(array[i].getDescription()), gbc);
            ++gbc.gridy;
        }
        this.nameField = new JTextField(NUM_CHAR);
        this.botanicalNameField = new JTextField(NUM_CHAR);
        gbc.gridy = 0;
        ++gbc.gridx;
        panel.add(nameField, gbc);
        ++gbc.gridy;
        panel.add(botanicalNameField, gbc);
        ++gbc.gridy;
        for (int i = 0; i < NUM_SPINNER; ++i) {
            final JSpinner spinner = factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA));
            this.spinnerList.add(spinner);
            panel.add(spinner, gbc);
            ++gbc.gridy;
        }
        superPanel.add(factory.createLabel("Insert the optimal values of the plant"));
        this.getJDialog().getContentPane().add(superPanel, BorderLayout.NORTH);
        this.getJDialog().getContentPane().add(panel);
    }

    @Override
    protected void addAction() {
        int i = 0;
        ControllerImpl.getController().createNewPlant(this.nameField.getText(), 
                this.botanicalNameField.getText(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().doubleValue(),
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().doubleValue(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().doubleValue(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().intValue(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().doubleValue(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().intValue(), 
                ((SpinnerNumberModel) this.spinnerList.get(i++).getModel()).getNumber().doubleValue());
    }


}
