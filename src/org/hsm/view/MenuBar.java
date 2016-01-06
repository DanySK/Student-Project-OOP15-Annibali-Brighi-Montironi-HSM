package org.hsm.view;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *The MenuBar component for the main frame.
 *
 */
public class MenuBar implements GUIComponent {

    private static final String MENU_FILE = "File";
    private static final String MENU_INFORMATION = "Information";
    private static final String MENU_HELP = "Help";
    private static final String MENU_ITEM_ADD_PLANT = "Add Plant";
    private static final String MENU_ITEM_EXIT = "Exit";
    private static final String MENU_ITEM_ABOUT = "About Hydroponic System Manager";
    private final JMenuBar bar = new JMenuBar();

    /**
     * Create the MenuBar.
     * @param frame the MainFrame for the MenuBar
     */
    public MenuBar(final MainFrame frame) {
        final JMenu file = new JMenu(MENU_FILE);
        final JMenu information = new JMenu(MENU_INFORMATION);
        final JMenu help = new JMenu(MENU_HELP);
        final JMenuItem addPlant = new JMenuItem(MENU_ITEM_ADD_PLANT);
        final JMenuItem exit = new JMenuItem(MENU_ITEM_EXIT);
        exit.addActionListener(e -> frame.exit());
        final JMenuItem about = new JMenuItem(MENU_ITEM_ABOUT);
        file.add(addPlant);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        information.setMnemonic(KeyEvent.VK_I);
        help.setMnemonic(KeyEvent.VK_H);
        help.add(about);
        addPlant.addActionListener(e -> new PlantAddDialog(frame.getFrame()).start());
        bar.add(file);
        bar.add(information);
        bar.add(help);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
