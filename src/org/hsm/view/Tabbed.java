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
    private final DatabaseTab databaseTab;
    private final PlantsTab plantsTab;
    private final GreenhouseTab greenhouseTab;

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
        tab.add("Greenhouse Composition", new JPanel());
        tab.add("Plants", this.plantsTab.getComponent());
        tab.add("Plants Database", this.databaseTab.getComponent());

        this.plantsTab.addObserver(this.greenhouseTab);

        this.panel.add(tab, BorderLayout.CENTER);
    }

    /**
     *Set the tabbed visible and usable.
     *@param state the visibility state.
     */
    public void setVisible(final boolean state) {
        this.tab.setVisible(state);
    }

    /**
     * Get the Database tab.
     * @return the database tab
     */
    public DatabaseTab getDatabaseTab() {
        return this.databaseTab;
    }

    /**
     * Get the Plants tab.
     * @return the plants tab
     */
    public PlantsTab getPlantsTab() {
        return this.plantsTab;
    }

    /**
     * Get the greenhouse tab.
     * @return the greenhouse tab
     */
    public GreenhouseTab getGreenhouseTab() {
        return this.greenhouseTab;
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
