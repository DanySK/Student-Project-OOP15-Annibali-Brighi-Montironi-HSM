package org.hsm.view;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *My GUI Factory implementation for Hsm.
 *
 */
public class MyGUIFactory implements GUIFactory {

    @Override
    public JButton createButton(final String name, final Icon image) {
        final JButton button = new JButton();
        button.setText(name);
        button.setIcon(image);
        return button;
    }

}
