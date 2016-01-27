package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import static org.hsm.view.PlantModelCharacteristics.BOTANICAL_NAME;
import static org.hsm.view.PlantModelCharacteristics.BRIGHTNESS;
import static org.hsm.view.PlantModelCharacteristics.CONDUCTIVITY;
import static org.hsm.view.PlantModelCharacteristics.COST;
import static org.hsm.view.PlantModelCharacteristics.GROWTH_TIME;
import static org.hsm.view.PlantModelCharacteristics.LIFE;
import static org.hsm.view.PlantModelCharacteristics.NAME;
import static org.hsm.view.PlantModelCharacteristics.PH;
import static org.hsm.view.PlantModelCharacteristics.SIZE;
import static org.hsm.view.PlantModelCharacteristics.TEMPERATURE;

import javax.swing.JComponent;
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
    private static final int INSET = 3;
    private static final double DELTA = 0.1;
    private static final String DIALOG_TITLE = "Create new Plant";
    private final Map<PlantModelCharacteristics, JComponent> map;

    /**
     * Create the add plant dialog.
     * @param frame
     * the main frame of the app
     */
    public PlantCreateDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.map = new HashMap<>();
        final GUIFactory factory = new MyGUIFactory();
        final JPanel superPanel = new JPanel();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.map.put(NAME, new JTextField(NUM_CHAR));
        this.map.put(BOTANICAL_NAME, new JTextField(NUM_CHAR));
        this.map.put(BRIGHTNESS, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(CONDUCTIVITY, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(COST, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(GROWTH_TIME, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(LIFE, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(PH, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(SIZE, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));
        this.map.put(TEMPERATURE, factory.createSpinner(NUM_CHAR, new SpinnerNumberModel(0, 0, 100, DELTA)));

        for (final PlantModelCharacteristics elem: PlantModelCharacteristics.values()) {
            panel.add(new JLabel(elem.toString()), gbc);
            ++gbc.gridx;
            panel.add(this.map.get(elem), gbc);
            ++gbc.gridy;
            gbc.gridx = 0;
        }

        superPanel.add(factory.createLabel("Insert the optimal values of the plant"));
        this.getJDialog().getContentPane().add(superPanel, BorderLayout.NORTH);
        this.getJDialog().getContentPane().add(panel);
    }

    @Override
    protected void addAction() {
        ControllerImpl.getController().createNewPlant(((JTextField) this.map.get(NAME)).getText(), 
                                                     ((JTextField) this.map.get(BOTANICAL_NAME)).getText(), 
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(PH)).getModel()).getNumber().doubleValue(), 
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(BRIGHTNESS)).getModel()).getNumber().doubleValue(),
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(CONDUCTIVITY)).getModel()).getNumber().doubleValue(), 
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(GROWTH_TIME)).getModel()).getNumber().intValue(), 
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(TEMPERATURE)).getModel()).getNumber().doubleValue(),
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(LIFE)).getModel()).getNumber().intValue(),
                                                     ((SpinnerNumberModel) ((JSpinner) this.map.get(SIZE)).getModel()).getNumber().doubleValue());
    }


}
