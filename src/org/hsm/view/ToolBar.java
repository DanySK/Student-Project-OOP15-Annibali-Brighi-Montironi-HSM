package org.hsm.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 *The class which create the toolbar for main frame.
 *
 */
public class ToolBar implements GUIComponent {

    private final JToolBar bar;

    /**
     *Create a toolbar.
     *@param frame
     *the main frame of the app
     */
    public ToolBar(final JFrame frame) {
        final GUIFactory factory = new MyGUIFactory();
        this.bar = new JToolBar("Toolbar");
        final JButton createGreenhouseButton = factory.createButton("Create GreenHouse", new ImageIcon(getClass().getResource("/new.png"))); 
        final JButton removeGreenhouseButton = factory.createButton("Remove GreenHouse", new ImageIcon(getClass().getResource("/delete.png")));
        final JButton openGreenhouseButton = factory.createButton("Open GreenHouse", new ImageIcon(getClass().getResource("/open.png")));
        final JButton saveGreenhouseButton = factory.createButton("Save", new ImageIcon(getClass().getResource("/save.png")));
        createGreenhouseButton.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        openGreenhouseButton.addActionListener(e -> new OpenFileDialog(frame).getPath());
        saveGreenhouseButton.addActionListener(e -> new SaveFileDialog(frame).getPath());
        bar.add(createGreenhouseButton);
        bar.add(openGreenhouseButton);
        bar.add(saveGreenhouseButton);
        bar.add(removeGreenhouseButton);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
