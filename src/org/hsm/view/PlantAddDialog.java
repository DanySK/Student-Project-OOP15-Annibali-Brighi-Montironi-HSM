package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *The frame for creating a new avaiable plant.
 *
 */
public class PlantAddDialog {

    private static final String FRAME_TITLE = "Add a plant";
    private static final String START_LABEL = "Chose the plant";
    private static final int MIN_X_DIMENSION = 300;
    private final JDialog dialog;
    private final String[] string = new String[]{"Pomodoro dei Cannibali dell Fiji", "Melanzana", "Mandarino"};

    /**
     *Create the dialog to add new plants.
     *@param frame
     *the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        this.dialog = new JDialog(frame, FRAME_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.dialog.setMinimumSize(new Dimension(MIN_X_DIMENSION, 0));
        final JPanel southPanel = new JPanel(new FlowLayout());
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        this.dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final JLabel label = new JLabel(START_LABEL);
        final JButton add = new JButton("Add");
        southPanel.add(add);
        final JComboBox<String> plantsList = new JComboBox<>(string);
        plantsList.setSelectedIndex(string.length - 1);
        northPanel.add(label);
        centerPanel.add(plantsList);
        this.dialog.add(northPanel, BorderLayout.NORTH);
        this.dialog.add(centerPanel);
        this.dialog.add(southPanel, BorderLayout.SOUTH);
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
