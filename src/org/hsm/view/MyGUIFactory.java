package org.hsm.view;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *My GUI Factory implementation for Hsm.
 *
 */
public class MyGUIFactory implements GUIFactory {

    private static final double SIZE_FACTOR = 1.5;

    @Override
    public JButton createButton(final String name, final Icon image) {
        final JButton button = new JButton();
        button.setText(name);
        button.setIcon(image);
        return button;
    }

    @Override
    public JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text);
        final Font font = label.getFont();
        final Font newFont = new Font("newFont", font.getStyle(), (int) (font.getSize() * SIZE_FACTOR));
        label.setFont(newFont);
        return label;
    }

}
