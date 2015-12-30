package org.hsm.view;

import javax.swing.JComponent;

/**
 *All the GUI components must implements this interface. 
 *
 */
public interface GUIComponent {

    /**
     * Get the component.
     * @return the component.
     */
    JComponent getComponent();

}
