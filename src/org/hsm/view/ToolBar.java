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
        final JButton createGreenhouseButton = factory.createButton("Create Greenhouse", new ImageIcon(getClass().getResource("/new.png"))); 
        final JButton removeGreenhouseButton = factory.createButton("Remove Greenhouse", new ImageIcon(getClass().getResource("/delete.png")));
        final JButton openGreenhouseButton = factory.createButton("Open Greenhouse", new ImageIcon(getClass().getResource("/open.png")));
        final JButton saveGreenhouseButton = factory.createButton("Save Greenhouse", new ImageIcon(getClass().getResource("/save.png")));
        final JButton importDatabaseButton = factory.createButton("Import Database", new ImageIcon(getClass().getResource("/import.png")));
        final JButton exportDatabaseButton = factory.createButton("Export Database", new ImageIcon(getClass().getResource("/export.png")));
        createGreenhouseButton.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        bar.add(createGreenhouseButton);
        bar.add(openGreenhouseButton);
        bar.add(saveGreenhouseButton);
        bar.add(removeGreenhouseButton);
        bar.addSeparator();
        bar.add(importDatabaseButton);
        bar.add(exportDatabaseButton);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
