package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *The frame which you can add plants in the table.
 *
 */
public class PlantAddFrame {

    private static final List<String> NAMES = new ArrayList<>(Arrays.asList("Nome", "Nome Latino", "ph", "Luminosità", "Temperatura"));
    private static final int NUM_CHAR = 15;
    private static final int NUM_ROW = NAMES.size();
    private static final int INSET = 3;
    private static final String TITLE = "Add Plant";
    private final JFrame frame;
    private final List<JTextField> fieldList;

    /**
     * Create the add plant frame.
     */
    public PlantAddFrame() {
        this.fieldList = new ArrayList<>();
        for (int i = 0; i < NAMES.size(); ++i) {
            this.fieldList.add(new JTextField(NUM_CHAR));
        }
        this.frame = new JFrame(TITLE);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        for (int i = 0; i < NUM_ROW; ++i) {
            gbc.gridy = i;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.LINE_END;
            panel.add(new JLabel(NAMES.get(i)), gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            panel.add(this.fieldList.get(i), gbc);
        }
        this.frame.getContentPane().add(panel);
        final JPanel southPanel = new JPanel(new FlowLayout());
        final JButton add = new JButton("Add");
        southPanel.add(add);
        add.addActionListener(e -> addPlant());
        this.frame.getContentPane().add(southPanel, BorderLayout.PAGE_END);
    }

    //QUESTO METODO RIMANE INUTILIZZATO DATE CHE NON HO A DISPOSIZIONE IL CONTROLLER
    private void addPlant() {
        final List<String> list = new ArrayList<>();
        for (final JTextField field: this.fieldList) {
            list.add(field.getText());
        }
        //CHIAMATA AL CONTROLLER CON L'INVIO DELLA LISTA DI INFORMAZIONI SU PIANTA DA AGGIUNGERE
    }

    /**
     *Set the frame visible.
     */
    public void start() {
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    /**
     * Test.
     * @param args args
     */
    public static void main(final String... args) {
        new PlantAddFrame().start();
    }
}
