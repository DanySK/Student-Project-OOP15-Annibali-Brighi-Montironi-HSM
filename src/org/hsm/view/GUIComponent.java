package org.hsm.view;

import javax.swing.JPanel;

/**
 *All the GUI components must implements this interface. 
 *
 */
public interface GUIComponent {

    /**
     * Get the panel of the component.
     * @return the panel of the component.
     */
    JPanel getMainPanel();

}
