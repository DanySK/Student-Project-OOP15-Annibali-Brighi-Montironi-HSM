package org.hsm.view;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 *The utility class used to show messages.
 *
 */
public final class Messages {

    private static final String ERROR = "Error";

    private Messages() { }

    /**
     *Show an error message.
     * @param component the component covered by the message
     * @param text the text of the message
     */
    public static void errorMessage(final Component component, final String text) {
        JOptionPane.showMessageDialog(component, text, ERROR, JOptionPane.ERROR_MESSAGE);
    }

}
