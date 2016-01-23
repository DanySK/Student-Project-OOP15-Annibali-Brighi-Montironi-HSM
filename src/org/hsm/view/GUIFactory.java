package org.hsm.view;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

/**
 *The interface for GUI Factory.
 *
 */
public interface GUIFactory {

    /**
     * Create a button.
     * @param name the name of the button.
     * @param image the image of the button.
     * @return the new JButton.
     */
    JButton createButton(final String name, final Icon image);

    /**
     * Create a new label with a specific size of font.
     * @param text the text to write in the label
     * @return a new JLabel.
     */
    JLabel createLabel(final String text);

    /**
     * Create a new JSpinner with the specific size.
     * @param size the size of the spinner.
     * @param model the spinner model.
     * @return a new JSpinner
     */
    JSpinner createSpinner(final int size, final SpinnerModel model);

}
