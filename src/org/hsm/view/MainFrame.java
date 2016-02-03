package org.hsm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;

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
    private final MenuBar menubar;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.setSystemLook();
        this.tab = new Tabbed(this.frame);
        this.tab.setEnable(false);
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                exit();
            }
        });
        final ToolBar toolbar = new ToolBar(this.frame);
        this.menubar = new MenuBar(this);
        this.frame.setJMenuBar((JMenuBar) this.menubar.getComponent());
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

    @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void setActive(final boolean status) {
        this.tab.setEnable(status);
        this.menubar.setEditEnable(status);
    }

    @Override
    public void insertGreenhouse() {
        this.tab.getGreenhouseTab().setGreenhouse();
    }

    @Override
    public void insertModelPlant(final String name, final String botanicalName, final double ph, final double brightness,
            final int optimalGrowthTime, final int life, final double size, final double conductivity,
            final double temperature) {
        this.tab.getDatabaseTab().insertRow(name, botanicalName, ph, brightness, optimalGrowthTime, life, size,
                conductivity, temperature);
    }

    @Override
    public void removeSelectedModelPlant() {
        this.tab.getDatabaseTab().removeSelectedRow();
    }

    @Override
    public void insertPlant(final int id, final String name, final int cost, final int ph, final double brightness,
                final double conductivity, final double temperature) {
        this.tab.getPlantsTab().insertRow(id, name, cost, ph, brightness, conductivity, temperature);
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

    @Override
    public Optional<String> saveGreenhouseDialog() {
        return new SaveFileDialog(this.frame, "HSM Document", "hsm").getPath();
    }

    @Override
    public Optional<String> loadGreenhouseDialog() {
        return new OpenFileDialog(this.frame, "HSM Document", "hsm").getPath();
    }

    @Override
    public Optional<String> exportDatabaseDialog() {
        return new SaveFileDialog(this.frame, "DHSM Document", "dat").getPath();
    }

    @Override
    public Optional<String> importDatabaseDialog() {
        return new OpenFileDialog(this.frame, "DHSM Document", "dat").getPath();
    }

    @Override
    public void clean() {
        this.tab.getPlantsTab().clean();
    }

}
