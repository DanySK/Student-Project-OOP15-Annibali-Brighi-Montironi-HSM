package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *The frame for creating a new avaiable plant.
 *
 */
public class PlantAddDialog extends AbstractAddDialog {

    private static final String DIALOG_TITLE = "Add a plant";
    private static final String START_LABEL = "Chose the plant";
    private static final int MIN_X_DIMENSION = 300;
    private final String[] string = new String[]{"Pomodoro dei Cannibali dell Fiji", "Melanzana", "Mandarino"};

    /**
     *Create the dialog to add new plants.
     *@param frame
     *the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.getJDialog().setMinimumSize(new Dimension(MIN_X_DIMENSION, 0));
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        final JLabel label = new JLabel(START_LABEL);
        final JComboBox<String> plantsList = new JComboBox<>(string);
        plantsList.setSelectedIndex(string.length - 1);
        northPanel.add(label);
        centerPanel.add(plantsList);
        this.getJDialog().add(northPanel, BorderLayout.NORTH);
        this.getJDialog().add(centerPanel);
    }

    @Override
    protected void addAction() {
        // TODO Auto-generated method stub
    }

}
