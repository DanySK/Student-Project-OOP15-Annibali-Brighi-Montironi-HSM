package org.hsm.view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *The class which create the toolbar for main frame.
 *
 */
public class ToolBar implements GUIComponent {

    private final JToolBar bar;
    private final JLabel label;

    /**
     *Create a toolbar.
     */
    public ToolBar() {
        this.bar = new JToolBar("Toolbar");
        this.label = new JLabel("No GreenHouse Selected");
        final JButton addGreenhouseButton = new JButton("Add GreenHouse");
        final JButton removeGreenhouseButton = new JButton("Remove GreenHouse");
        bar.add(addGreenhouseButton);
        bar.add(removeGreenhouseButton);
        bar.add(Box.createHorizontalGlue());
        bar.add(new JLabel("GreenHouse:  "));
        bar.add(this.label);
    }

    /**
     * Set the greenhouse name in the label.
     * @param name the nameto insert in the label
     */
    public void setGreenhouseName(final String name) {
        this.label.setText(name);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
