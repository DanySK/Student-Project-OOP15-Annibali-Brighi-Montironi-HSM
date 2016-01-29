package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collections;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.PlantModel;

/**
 *The dialog for creating a new avaiable plant.
 *
 */
public class PlantAddDialog extends AbstractAddDialog {

    private static final String DIALOG_TITLE = "Add a plant";
    private static final String START_LABEL = "Chose the plant";
    private JComboBox<Object> plantsList;
    private final EuroPanel euroPanel;

    /**
     *Create the dialog to add new plants.
     *@param frame
     *the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        Set<String> set = null;
        try {
            set = ControllerImpl.getController().getDatabase().getDb().keySet();
            this.plantsList = new JComboBox<>(set.toArray());
            plantsList.setSelectedIndex(0);
        } catch (Exception e) {
            set = Collections.emptySet();
            this.plantsList = new JComboBox<>(set.toArray());
        }
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel typeLabel = new JLabel("Type:");
        centerPanel.add(typeLabel, gbc);
        ++gbc.gridx;
        centerPanel.add(this.plantsList, gbc);
        ++gbc.gridy;
        gbc.gridx = 0;
        final JLabel costLabel = new JLabel("Cost:");
        centerPanel.add(costLabel, gbc);
        ++gbc.gridx;
        this.euroPanel = new EuroPanelImpl();
        centerPanel.add((JPanel) this.euroPanel, gbc);
        final JLabel label = new MyGUIFactory().createLabel(START_LABEL);
        northPanel.add(label);
        this.getJDialog().add(northPanel, BorderLayout.NORTH);
        this.getJDialog().add(centerPanel);
        this.start();
    }

    @Override
    protected void addAction() {
        final String choice = (String) this.plantsList.getSelectedItem();
        final PlantModel model = ControllerImpl.getController().getDatabase().getDb().get(choice);
        ControllerImpl.getController().addPlants(1, model, this.euroPanel.getValue());
        getJDialog().dispose();
    }

}
