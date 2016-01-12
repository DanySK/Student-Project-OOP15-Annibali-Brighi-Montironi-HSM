package org.hsm.view;

import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *The Open file dialog with a file chooser.
 *
 */
public class OpenFileDialog extends AbstractFileDialog {

    /**
     * Create a dialog for saving files.
     * @param frame the frame for the dialog
     */
    public OpenFileDialog(final JFrame frame) {
        super(frame);
    }

    @Override
    public Optional<String> getPath() {
        if (super.getFileChooser().showOpenDialog(super.getFrame()) == JFileChooser.APPROVE_OPTION) {
            return Optional.of(super.getFileChooser().getSelectedFile().getAbsolutePath());
        } else {
            return Optional.empty();
        }
    }
}
