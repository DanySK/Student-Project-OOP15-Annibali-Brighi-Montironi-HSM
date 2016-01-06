package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *This frame can be used to create a new greenhouse.
 *
 */
public class GreenhouseCreateDialog extends AbstractAddDialog {

    private static final String DIALOG_TITLE = "Create new Greenhouse";
    private static final String LABEL_NAME = "Greenhouse name : ";
    private static final String LABEL_TYPE = "Greenhouse type : ";
    private static final String LABEL_SIZE = "Greenhouse size (m3) : ";
    private static final String LABEL_INIT = "Insert information about the new Greenhouse";
    private static final int START_VALUE = 100;
    private static final int MAX_VALUE = 10000;
    private static final int INSET = 5;
    private static final int TXT_DIM = 20;
    private static final int SPINNER_TXT_DIM = 18;
    private final JLabel pictureLabel;

    /**
     *Create the dialog to make a new Greenhouse.
     *@param frame
     *the main frame of the app.
     */
    public GreenhouseCreateDialog(final JFrame frame) {
        super(frame, DIALOG_TITLE, Dialog.ModalityType.APPLICATION_MODAL);
        this.pictureLabel = new JLabel("", new ImageIcon("res/linear.jpg"), JLabel.CENTER);
        final JPanel panelUp = new JPanel();
        panelUp.add(new JLabel(LABEL_INIT));
        final JLabel name = new JLabel(LABEL_NAME);
        final JLabel type = new JLabel(LABEL_TYPE);
        final JLabel size = new JLabel(LABEL_SIZE);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(name, gbc);
        ++gbc.gridx;
        final JTextField text = new JTextField(TXT_DIM);
        panel.add(text, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        panel.add(size, gbc);
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(START_VALUE, 1, MAX_VALUE, 1));
        final Component mySpinnerEditor = spinner.getEditor();
        final JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(SPINNER_TXT_DIM);
        ++gbc.gridx;
        panel.add(spinner, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        panel.add(type, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        final JRadioButton linearButton = new JRadioButton("linear");
        linearButton.setActionCommand("linear");
        linearButton.setSelected(true);
        linearButton.addActionListener(new AdapterImageHandler());
        final JRadioButton circularButton = new JRadioButton("circular");
        circularButton.setActionCommand("circular");
        circularButton.addActionListener(new AdapterImageHandler());
        final JRadioButton reticularButton = new JRadioButton("reticular");
        reticularButton.setActionCommand("reticular");
        reticularButton.addActionListener(new AdapterImageHandler());
        final JRadioButton pyramidalButton = new JRadioButton("pyramidal");
        pyramidalButton.setActionCommand("pyramidal");
        pyramidalButton.addActionListener(new AdapterImageHandler());
        final ButtonGroup group = new ButtonGroup();
        group.add(linearButton);
        group.add(circularButton);
        group.add(reticularButton);
        group.add(pyramidalButton);
        final JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        panelButtons.add(linearButton);
        panelButtons.add(circularButton);
        panelButtons.add(reticularButton);
        panelButtons.add(pyramidalButton);
        panel.add(panelButtons, gbc);
        ++gbc.gridx;
        panel.add(this.pictureLabel, gbc);
        this.getJDialog().getContentPane().add(panelUp, BorderLayout.NORTH);
        this.getJDialog().getContentPane().add(panel);
    }

    private class AdapterImageHandler implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            final JRadioButton button = (JRadioButton) e.getSource();
            switch (button.getActionCommand()) {
            case "linear" : 
                pictureLabel.setIcon(new ImageIcon("res/linear.jpg"));
                return;
            case "reticular" : 
                pictureLabel.setIcon(new ImageIcon("res/reticular.jpg"));
                return;
            case "pyramidal" : 
                pictureLabel.setIcon(new ImageIcon("res/pyramidal.jpg"));
                return; 
            case "circular" : 
                pictureLabel.setIcon(new ImageIcon("res/circular.jpg"));
                return; 
            default :
                pictureLabel.setIcon(new ImageIcon("res/linear.jpg"));
            }
        }

    }

    @Override
    protected void addAction() {
        // TODO Auto-generated method stub
    }

}
