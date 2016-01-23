package org.hsm.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

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

    @Override
    public JSpinner createSpinner(final int size, final SpinnerModel model) {
        final JSpinner spin = new JSpinner(model);
        final Component mySpinnerEditor = spin.getEditor();
        final JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(size);
        return spin;
    }

}
