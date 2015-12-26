package org.hsm.view;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *The interface for GUI Factory.
 *
 */
public interface GUIFactory {

    /**
     * Create a button.
     * @param name the name of the button.
     * @param image the image of the button.
     * @return the new button.
     */
    JButton createButton(final String name, final Icon image);

}
