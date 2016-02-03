package org.hsm.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *The utility class used to show messages.
 *
 */
public final class Utilities {

    private static final String ERROR = "Error";
    private static final String SAVE = "Save";

    private Utilities() { }

    /**
     *Show an error message.
     * @param component the component covered by the message
     * @param text the text of the message
     */
    public static void errorMessage(final Component component, final String text) {
        JOptionPane.showMessageDialog(component, text, ERROR, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show the message for saving greenhouse.
     * @param component the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveGreenhouseMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Greenhouse?", 
           SAVE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Show the message for saving database.
     * @param component the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveDatabaseMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Database?", 
           SAVE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Show the message for saving the database and the greenhouse.
     * @param component the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveGreenhouseAndDBMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Grenhouse and the Database?", 
           SAVE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Get the ImageIcon of the structure.
     * @param type the type of structure
     * @return the ImageIcon of the structure
     */
    public static ImageIcon getStructIcon(final String type) {
        switch (type) {
        case "linear" : 
            return new ImageIcon(Utilities.class.getClass().getResource("/linear.jpg"));
        case "reticular" : 
            return new ImageIcon(Utilities.class.getClass().getResource("/reticular.jpg"));
        case "pyramidal" : 
            return new ImageIcon(Utilities.class.getClass().getResource("/pyramidal.jpg"));
        case "circular" : 
            return new ImageIcon(Utilities.class.getClass().getResource("/circular.jpg"));
        default :
            return new ImageIcon(Utilities.class.getClass().getResource("/linear.jpg"));
        }
    }


}
