package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.hsm.controller.ControllerImpl;
import org.hsm.model.PlantModel;

/**
 *The dialog for creating a new avaiable plant.
 *
 */
public class PlantAddDialog extends AbstractAddDialog {

    private static final String DIALOG_TITLE = "Add a plant";
    private static final String START_LABEL = "Chose the plant";
    private static final int MIN_X_DIMENSION = 300;
    private static final int MAX_COST = 1000000;
    private static final int MAX_CENT = 99;
    private static final int CENT_FACTOR = 100;
    private JComboBox<Object> plantsList;
    private final JSpinner euroSpinner;
    private final JSpinner centSpinner;

    /**
     *Create the dialog to add new plants.
     *@param frame
     *the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.getJDialog().setMinimumSize(new Dimension(MIN_X_DIMENSION, 0));
        Set<String> set = null;
        try {
            set = ControllerImpl.getController().getDatabase().getDb().keySet();
            this.plantsList = new JComboBox<>(set.toArray());
        } catch (Exception e) {
            Messages.errorMessage(frame, "There isn't a database for catching plants!");
            //DEBUGGING
            this.plantsList = new JComboBox<>(new Object[]{});
            //return;
        }
        //plantsList.setSelectedIndex(0);
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        final JLabel typeLabel = new JLabel("Type:");
        final JPanel euroPanel = new JPanel(new FlowLayout());
        this.euroSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_COST, 1));
        this.centSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_CENT, 1));
        final JLabel virgola = new JLabel(", ");
        euroPanel.add(this.euroSpinner);
        euroPanel.add(virgola);
        euroPanel.add(this.centSpinner);
        centerPanel.add(typeLabel, gbc);
        ++gbc.gridx;
        centerPanel.add(this.plantsList, gbc);
        ++gbc.gridy;
        gbc.gridx = 0;
        final JLabel costLabel = new JLabel("Cost:");
        centerPanel.add(costLabel, gbc);
        ++gbc.gridx;
        centerPanel.add(euroPanel, gbc);
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
        final int cent = ((SpinnerNumberModel) this.centSpinner.getModel()).getNumber().intValue();
        final int euro = ((SpinnerNumberModel) this.euroSpinner.getModel()).getNumber().intValue() * CENT_FACTOR;
        ControllerImpl.getController().addPlants(1, model, cent + euro);
    }

}
