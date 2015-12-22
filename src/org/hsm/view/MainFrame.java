package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

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
    private final String[] columnsName = {"Nome", "Nome Latino", "ph", "Luminosità", "Temperatura"};
    private final Object[][] data = {
        {"Pomodoro", "Solanum lycopersicum", "4", "1475", "21"},
        {"Mandarino", "Citrus reticulata", "2", "1765", "25"}
    };
    /**
     * The main frame only constructor.
     */
    public MainFrame() {
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTabbedPane tabel = new JTabbedPane();
        final JTable table = new JTable(data, columnsName);
        table.setAutoCreateRowSorter(true);
        final JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tabel.add("Plants", scrollPane);
        tabel.add("Other tabel", new JLabel("Other tabel"));
        this.frame.getContentPane().add(tabel, BorderLayout.CENTER);
        final JMenuBar menuBar = new JMenuBar();
        final JMenu file = new JMenu(MENU_FILE);
        final JMenuItem addPlant = new JMenuItem(MENU_ITEM_ADD_PLANT);
        final JMenuItem exit = new JMenuItem(MENU_ITEM_EXIT);
        exit.addActionListener(e -> System.exit(0));
        file.add(addPlant);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        menuBar.add(file);
        this.frame.getContentPane().add(menuBar, BorderLayout.PAGE_START);
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

    /**
     * Prova per la visualizzazione.
     * @param args
     * inutili argomenti
     */
    public static void main(final String... args) {
        new MainFrame().start();
    }

}
