package org.hsm.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *The low panel which contains the most important option.
 *
 */
public class LowPanel implements GUIComponent {

    private final JPanel panel;

    /**
     * Create low panel for options.
     * @param frame
     * the maine frame of the app
     */
    public LowPanel(final JFrame frame) {
        this.panel = new JPanel();
        final FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        this.panel.setLayout(layout);
        final JButton add = new JButton("Add Plant");
        add.addActionListener(e -> {
            new PlantAddDialog(frame).start();
        });
        final JButton remove = new JButton("Remove Plant");
        final JButton createPlant = new JButton("Create new Plant");
        createPlant.addActionListener(e -> {
            new PlantCreateDialog(frame).start();
        });
        this.panel.add(add);
        this.panel.add(remove);
        this.panel.add(createPlant);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
