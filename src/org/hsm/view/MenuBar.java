package org.hsm.view;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.hsm.controller.ControllerImpl;

/**
 *The MenuBar component for the main frame.
 *
 */
public class MenuBar implements GUIComponent {

    private static final String MENU_FILE = "File";
    private static final String MENU_INFORMATION = "Information";
    private static final String MENU_EDIT = "Edit";
    private static final String MENU_HELP = "Help";
    private static final String MENU_ITEM_CREATE_GREENHOUSE = "New Greenhouse";
    private static final String MENU_ITEM_SAVE_GREENHOUSE = "Save Greenhouse";
    private static final String MENU_ITEM_ADD_PLANT = "Add Plant";
    private static final String MENU_ITEM_REMOVE_GREENHOUSE = "Remove Greenhouse";
    private static final String MENU_ITEM_LOAD_GREENHOUSE = "Open Greenhouse";
    private static final String MENU_ITEM_NEW_DATABASE = "New Database";
    private static final String MENU_ITEM_IMPORT_DATABASE = "Import Database";
    private static final String MENU_ITEM_EXPORT_DATABASE = "Export Database";
    private static final String MENU_ITEM_EXIT = "Exit";
    private static final String MENU_ITEM_ABOUT = "About Hydroponic System Manager";
    private final JMenuBar bar;

    /**
     * Create the MenuBar.
     * @param frame the MainFrame for the MenuBar
     */
    public MenuBar(final MainFrame frame) {
        //Menù list
        this.bar = new JMenuBar();
        final JMenu file = new JMenu(MENU_FILE);
        final JMenu edit = new JMenu(MENU_EDIT);
        final JMenu information = new JMenu(MENU_INFORMATION);
        final JMenu help = new JMenu(MENU_HELP);
        //Menù Item list
        final JMenuItem addPlant = new JMenuItem(MENU_ITEM_ADD_PLANT);
        final JMenuItem newGreenhouse = new JMenuItem(MENU_ITEM_CREATE_GREENHOUSE);
        final JMenuItem saveGreenhouse = new JMenuItem(MENU_ITEM_SAVE_GREENHOUSE);
        final JMenuItem loadGreenhouse = new JMenuItem(MENU_ITEM_LOAD_GREENHOUSE);
        final JMenuItem removeGreenhouse = new JMenuItem(MENU_ITEM_REMOVE_GREENHOUSE);
        final JMenuItem newDatabase = new JMenuItem(MENU_ITEM_NEW_DATABASE);
        final JMenuItem importDatabase = new JMenuItem(MENU_ITEM_IMPORT_DATABASE);
        final JMenuItem exportDatabase = new JMenuItem(MENU_ITEM_EXPORT_DATABASE);
        final JMenuItem exit = new JMenuItem(MENU_ITEM_EXIT);
        final JMenuItem about = new JMenuItem(MENU_ITEM_ABOUT);
        //Insert item in Menu
        file.add(newGreenhouse);
        file.add(saveGreenhouse);
        file.add(loadGreenhouse);
        file.add(removeGreenhouse);
        file.addSeparator();
        file.add(newDatabase);
        file.add(importDatabase);
        file.add(exportDatabase);
        edit.add(addPlant);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);
        information.setMnemonic(KeyEvent.VK_I);
        help.setMnemonic(KeyEvent.VK_H);
        help.add(about);
        //Add listeners at items
        newGreenhouse.addActionListener(e -> new GreenhouseCreateDialog(frame.getFrame()).start());
        loadGreenhouse.addActionListener(e -> ControllerImpl.getController().loadGreenhouse());
        saveGreenhouse.addActionListener(e -> ControllerImpl.getController().saveGreenhouse());
        importDatabase.addActionListener(e -> ControllerImpl.getController().loadDatabase());
        exportDatabase.addActionListener(e -> ControllerImpl.getController().saveDatabase());
        addPlant.addActionListener(e -> new PlantAddDialog(frame.getFrame()).start());
        exit.addActionListener(e -> frame.exit());
        edit.setEnabled(false);
        bar.add(file);
        bar.add(edit);
        bar.add(information);
        bar.add(help);
    }

    /**
     * Set the state of the Edit Menu.
     * @param state the state of the menu
     */
    public void setEditEnable(final boolean state) {
        this.bar.getMenu(1).setEnabled(state);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
