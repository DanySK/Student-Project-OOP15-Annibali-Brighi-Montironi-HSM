package org.hsm.view;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
     *@param frame
     *the main frame of the app
     */
    public ToolBar(final JFrame frame) {
        final GUIFactory factory = new MyGUIFactory();
        this.bar = new JToolBar("Toolbar");
        this.label = new JLabel("NO GREENHOUSE SELECTED");
        final JButton createGreenhouseButton = factory.createButton("Create GreenHouse", new ImageIcon("res/new.png")); 
        final JButton removeGreenhouseButton = factory.createButton("Remove GreenHouse", new ImageIcon("res/delete.png"));
        final JButton openGreenhouseButton = factory.createButton("Open GreenHouse", new ImageIcon("res/open.png"));
        final JButton saveGreenhouseButton = factory.createButton("Save", new ImageIcon("res/save.png"));
        createGreenhouseButton.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        openGreenhouseButton.addActionListener(e -> new JFileChooser().showOpenDialog(bar));
        bar.add(createGreenhouseButton);
        bar.add(openGreenhouseButton);
        bar.add(saveGreenhouseButton);
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
