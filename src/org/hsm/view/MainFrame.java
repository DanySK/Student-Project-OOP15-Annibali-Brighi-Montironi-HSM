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
public class MainFrame implements UserInteface {

    private static final String FRAME_TITLE = "Hydroponic System Manager";
    private static final double PROPORTION = 1.3;
    private final JFrame frame;
    private final Tabbed tabel;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.setSystemLook();
        this.tabel = new Tabbed();
        this.frame = new JFrame(FRAME_TITLE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                exit();
            }
        });
        final GUIComponent lowPanel = new LowPanel(this.frame);
        final ToolBar toolbar = new ToolBar(this.frame);
        final MenuBar menuBar = new MenuBar(this);
        this.frame.setJMenuBar((JMenuBar) menuBar.getComponent());
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

    /**
     * Get the JFrame of the MainFrame.
     * @return the JFrame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     *The exit procedure.
     */
    protected void exit() {
        if (JOptionPane.showConfirmDialog(frame, "Do you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
            //metodo del controller che salva il modello sul file
        }
    }

    @Override
    public void insertPlant(final Object... plant) {
        this.tabel.insertRow(plant);
    }

    @Override
    public void removeSelectedPlant() {
        this.tabel.removeSelectedRow();
        //chiama il metodo del controller che rimuove la pianta
    }
    //CREARE UN METODO CHE INSERISCI LE PIANTE DELLA SERRA ATTRAVERSO LETTURA FILE UTILIZZANDO THREAD (SWINGUTILITIES. INVOKE-LATER)

    private void setSystemLook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("errore visualizzazione gui");
        }
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
