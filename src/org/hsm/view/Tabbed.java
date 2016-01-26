package org.hsm.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

/**
 *The class which create and use the Tabbes.
 *
 */
public class Tabbed implements GUIComponent {

    private final JPanel panel;
    private final JTabbedPane tab;
    private final GUIComponent databaseTab;
    private final GUIComponent plantsTab;
    private final GUIComponent greenhouseTab;

    /**
     *Create the Tabbes.
     *@param frame the main frame of the app
     */
    public Tabbed(final JFrame frame) {
        this.tab = new JTabbedPane();
        this.panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        this.greenhouseTab = new GreenhouseTab();
        this.plantsTab = new PlantsTab(frame);
        this.databaseTab = new DatabaseTab(frame);

        tab.add("Greenhouse", this.greenhouseTab.getComponent());
        tab.add("Plants", this.plantsTab.getComponent());
        tab.add("Plants Database", this.databaseTab.getComponent());
        tab.add("Graphic", new JPanel());

        this.panel.add(tab, BorderLayout.CENTER);
    }

    /**
     *Set the tabbed visible and usable.
     *@param bol the visibility status.
     */
    public void setEnable(final boolean bol) {
        this.tab.setVisible(bol);
    }

    /**
     * Get the Database tab.
     * @return the database tab
     */
    public DatabaseTab getDatabaseTab() {
        return (DatabaseTab) this.databaseTab;
    }

    /**
     * Get the Plants tab.
     * @return the plants tab
     */
    public PlantsTab getPlantsTab() {
        return (PlantsTab) this.plantsTab;
    }

    /**
     * Get the greenhouse tab.
     * @return the greenhouse tab
     */
    public GreenhouseTab getGreenhouseTab() {
        return (GreenhouseTab) this.greenhouseTab;
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
