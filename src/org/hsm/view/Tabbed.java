package org.hsm.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

/**
 *The class which create and use the Tabel.
 *
 */
public class Tabbed implements GUIComponent {

    private final JPanel panel;
    private final JTabbedPane tab;

    /**
     *Create the Tabel.
     *@param frame the main frame of the app
     */
    public Tabbed(final JFrame frame) {
        this.tab = new JTabbedPane();
        this.panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        final GUIComponent greenhouseTab = new GreenhouseTab();
        final GUIComponent plantsTab = new PlantsTab(frame);

        tab.add("Greenhouse", greenhouseTab.getComponent());
        tab.add("Plants", plantsTab.getComponent());
        tab.add("Graphic", new JPanel());
        this.panel.add(tab, BorderLayout.CENTER);
    }

    /**
     *Set the tabbed visible and usable.
     */
    public void activeTabbed() {
        this.tab.setVisible(true);
    }

    /**
     *Set the tabbed disabled and unusable.
     */
    public void disableTabbed() {
        this.tab.setVisible(false);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
