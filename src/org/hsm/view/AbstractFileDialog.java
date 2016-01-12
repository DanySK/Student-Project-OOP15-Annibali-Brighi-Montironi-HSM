package org.hsm.view;

import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *The abstract class for file choosers.
 *
 */
public abstract class AbstractFileDialog implements FileDialog {

    private final JFileChooser fileChooser;
    private final JFrame frame;

    /**
     * Create the dialog used for choosing a file.
     * @param frame the frame for the dialog.
     */
    public AbstractFileDialog(final JFrame frame) {
        this.frame = frame;
        this.fileChooser = new JFileChooser();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("HSM documents", "hsm"));
    }

    /**
     * Get the JFileChooser component.
     * @return the JFileChooser
     */
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }

    /**
     * Get the JFrame for the JFileChooser position.
     * @return the JFrame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public abstract Optional<String> getPath();

}
