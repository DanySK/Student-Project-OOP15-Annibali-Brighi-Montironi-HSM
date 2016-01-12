package org.hsm.view;

import java.io.File;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *The Save file dialog with a file chooser.
 *
 */
public class SaveFileDialog extends AbstractFileDialog {

    /**
     * Create a dialog for saving files.
     * @param frame the frame for the dialog
     */
    public SaveFileDialog(final JFrame frame) {
        super(frame);
        super.getFileChooser().setSelectedFile(new File("New.hsm"));
    }

    @Override
    public Optional<String> getPath() {
        if (super.getFileChooser().showSaveDialog(super.getFrame()) == JFileChooser.APPROVE_OPTION) {
            String fileName = super.getFileChooser().getSelectedFile().getAbsolutePath();
            if (!fileName.endsWith(".hsm")) {
                fileName = new StringBuffer(fileName).append(".hsm").toString();
            }
            return Optional.of(fileName);
        } else {
            return Optional.empty();
        }
    }

}
