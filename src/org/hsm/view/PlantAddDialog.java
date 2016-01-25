package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hsm.controller.ControllerImpl;

/**
 *The dialog for creating a new avaiable plant.
 *
 */
public class PlantAddDialog extends AbstractAddDialog {

    private static final String DIALOG_TITLE = "Add a plant";
    private static final String START_LABEL = "Chose the plant";
    private static final int MIN_X_DIMENSION = 300;

    /**
     *Create the dialog to add new plants.
     *@param frame
     *the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.getJDialog().setMinimumSize(new Dimension(MIN_X_DIMENSION, 0));
        //CONTROLLO SE IL DATABASE è PRESENTE E POI TIRI ECCEZIONE IN VIDEO
        Set<String> set = null;
        try {
            set = ControllerImpl.getController().getDatabase().getDb().keySet();
        } catch (Exception e) {
            Messages.errorMessage(frame, "There isn't a database for catching plants!");
            return;
        }
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        final JLabel label = new JLabel(START_LABEL);
        final JComboBox<Object> plantsList = new JComboBox<>(set.toArray());
        plantsList.setSelectedIndex(set.size() - 1);
        northPanel.add(label);
        centerPanel.add(plantsList);
        this.getJDialog().add(northPanel, BorderLayout.NORTH);
        this.getJDialog().add(centerPanel);
        this.start();
    }

    @Override
    protected void addAction() {
        // TODO Auto-generated method stub
    }

}
