package org.hsm.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *The low panel which contains the most important option.
 *
 */
public class LowPanel implements GUIComponent {

    private final JPanel panel;

    /**
     * Create low panel for options.
     */
    public LowPanel() {
        this.panel = new JPanel();
        final FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        this.panel.setLayout(layout);
        final JButton add = new JButton("Add Plant");
        add.addActionListener(e -> {
            new PlantAddFrame().start();
        });
        final JButton remove = new JButton("Remove Plant");
        final JButton createPlant = new JButton("Create new Plant");
        this.panel.add(add);
        this.panel.add(remove);
        this.panel.add(createPlant);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
