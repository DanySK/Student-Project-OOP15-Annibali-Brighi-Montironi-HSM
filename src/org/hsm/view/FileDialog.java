package org.hsm.view;

import java.util.Optional;

/**
 *This interface includes methods for classes that work with file paths.
 *
 */
public interface FileDialog {

    /**
     * This method return the string of the path choosen by the dialog. 
     * If the path is not correct, it returns an empty Optional.
     * @return the path returned by the dialog.
     */
    Optional<String> getPath();

}
