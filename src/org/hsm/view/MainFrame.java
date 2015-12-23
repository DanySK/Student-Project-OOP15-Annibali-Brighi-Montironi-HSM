package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * The main frame of the application.
 *
 */
public class MainFrame {

    private static final String FRAME_TITLE = "Hydroponic System Manager";
    private static final String MENU_FILE = "File";
    private static final String MENU_ITEM_ADD_PLANT = "Add Plant";
    private static final String MENU_ITEM_EXIT = "Exit";
    private static final double PROPORTION = 1.3;
    private final JFrame frame;
    private final Tabel tabel;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.tabel = new Tabel();
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JMenuBar menuBar = new JMenuBar();
        final JMenu file = new JMenu(MENU_FILE);
        final JMenuItem addPlant = new JMenuItem(MENU_ITEM_ADD_PLANT);
        final JMenuItem exit = new JMenuItem(MENU_ITEM_EXIT);
        exit.addActionListener(e -> this.exit());
        file.add(addPlant);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        menuBar.add(file);
        final GUIComponent lowPanel = new LowPanel();
        this.frame.getContentPane().add(menuBar, BorderLayout.PAGE_START);
        this.frame.getContentPane().add(this.tabel.getMainPanel());
        this.frame.getContentPane().add(lowPanel.getMainPanel(), BorderLayout.PAGE_END);
    }

    /**
     * Set the main frame visible.
     */
    public void start() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) (screen.getWidth() / PROPORTION);
        final int sh = (int) (screen.getHeight() / PROPORTION);
        this.frame.setSize(sw, sh);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    private void exit() {
        if (JOptionPane.showConfirmDialog(frame, "Do you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }

    /**
     * Insert a plant into database.
     * @param plant
     * The plant to insert.
     */
    public void insertPlant(final Object... plant) {
        this.tabel.insertRow(plant);
    }
    /**
     * Prova per la visualizzazione.
     * @param args
     * inutili argomenti
     */
    public static void main(final String... args) {
        new MainFrame().start();
    }

}
