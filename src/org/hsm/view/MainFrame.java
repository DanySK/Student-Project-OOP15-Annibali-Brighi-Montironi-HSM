package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main frame of the application.
 *
 */
public class MainFrame implements View {

    private static final String FRAME_TITLE = "Hydroponic System Manager";
    private static final double PROPORTION = 1.3;
    private final JFrame frame;
    private final Tabbed tab;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.setSystemLook();
        this.tab = new Tabbed(this.frame);
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                exit();
            }
        });
        final ToolBar toolbar = new ToolBar(this.frame);
        final MenuBar menuBar = new MenuBar(this);
        this.frame.setJMenuBar((JMenuBar) menuBar.getComponent());
        this.frame.getContentPane().add(this.tab.getComponent());
        this.frame.getContentPane().add(toolbar.getComponent(), BorderLayout.PAGE_START);
    }

    @Override
    public void start() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) (screen.getWidth() / PROPORTION);
        final int sh = (int) (screen.getHeight() / PROPORTION);
        this.frame.setSize(sw, sh);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    /**
     * Get the JFrame of the MainFrame.
     * @return the JFrame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void setActive(final boolean status) {
        this.tab.setEnable(status);
    }

    @Override
    public void insertGreenhouse() {
        this.tab.getGreenhouseTab().setGreenhouse();
    }

    @Override
    public void insertModelPlant(final Object... plant) {
        this.tab.getDatabaseTab().insertRow(plant);
    }

    @Override
    public void removeSelectedModelPlant() {
        this.tab.getDatabaseTab().removeSelectedRow();
    }

    @Override
    public void insertPlant(final Object... plant) {
        this.tab.getPlantsTab().insertRow(plant);
    }

    @Override
    public void removeSelectedPlant() {
        this.tab.getPlantsTab().removeSelectedRow();
    }

    /**
     *The exit procedure whithout svaing.
     */
    public void exit() {
        if (JOptionPane.showConfirmDialog(frame, "Do you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void setSystemLook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("errore visualizzazione gui");
        }
    }

    /**
     * MAIN di prova.
     * @param strings eded
    */
    public static void main(final String...strings) {
        final View view = new MainFrame();
        view.setActive(true);
        view.start();
    }

}
