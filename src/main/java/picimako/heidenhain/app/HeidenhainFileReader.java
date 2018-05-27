package picimako.heidenhain.app;

import static picimako.heidenhain.util.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Reads the contents of a file.
 *
 * @author Tamas Balog
 */
public final class HeidenhainFileReader {

    private HeidenhainFileReader() {
        //prevent instantiation
    }

    /**
     * Reads the contents of a file defined by the argument path.
     *
     * @param path the file path
     * @return the content of the file as a list of rows
     */
    public static List<String> read(String path) {
        File file = new File(path);
        try {
            checkState(file.canRead(), "The file is not readable: " + file.getAbsolutePath());
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read the file: " + file.getAbsolutePath(), e);
        }
    }
}
