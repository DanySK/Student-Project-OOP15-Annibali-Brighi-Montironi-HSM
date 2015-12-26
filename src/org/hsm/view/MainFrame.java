package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * The main frame of the application.
 *
 */
public class MainFrame implements UserInteface {

    private static final String FRAME_TITLE = "Hydroponic System Manager";
    private static final String MENU_FILE = "File";
    private static final String MENU_INFORMATION = "Information";
    private static final String MENU_HELP = "Help";
    private static final String MENU_ITEM_ADD_PLANT = "Add Plant";
    private static final String MENU_ITEM_EXIT = "Exit";
    private static final String MENU_ITEM_ABOUT = "About Hydroponic System Manager";
    private static final double PROPORTION = 1.3;
    private final JFrame frame;
    private final Tabel tabel;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.tabel = new Tabel();
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                exit();
            }
        });
        final JMenuBar menuBar = new JMenuBar();
        final JMenu file = new JMenu(MENU_FILE);
        final JMenu information = new JMenu(MENU_INFORMATION);
        final JMenu help = new JMenu(MENU_HELP);
        final JMenuItem addPlant = new JMenuItem(MENU_ITEM_ADD_PLANT);
        final JMenuItem exit = new JMenuItem(MENU_ITEM_EXIT);
        final JMenuItem about = new JMenuItem(MENU_ITEM_ABOUT);
        exit.addActionListener(e -> this.exit());
        file.add(addPlant);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        information.setMnemonic(KeyEvent.VK_I);
        help.setMnemonic(KeyEvent.VK_H);
        help.add(about);
        menuBar.add(file);
        menuBar.add(information);
        menuBar.add(help);
        final GUIComponent lowPanel = new LowPanel();
        final ToolBar toolbar = new ToolBar();
        this.frame.setJMenuBar(menuBar);
        this.frame.getContentPane().add(this.tabel.getComponent());
        this.frame.getContentPane().add(toolbar.getComponent(), BorderLayout.PAGE_START);
        this.frame.getContentPane().add(lowPanel.getComponent(), BorderLayout.PAGE_END);
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

    @Override
    public void insertPlant(final Object... plant) {
        this.tabel.insertRow(plant);
    }

    @Override
    public void removeSelectedPlant() {
        this.tabel.removeSelectedRow();
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
