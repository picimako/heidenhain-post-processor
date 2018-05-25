package picimako.heidenhain.process;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Saves file content to a predefined file path.
 *
 * @author Tamas Balog
 */
final class PostProcessedFileWriter {

    /**
     * Saves the argument {@code outputContent} to the {@code outputPath}.
     *
     * @param outputPath the file path to write to
     * @param outputContent the content to write into the output file
     */
    void savePostProcessedContentToFile(String outputPath, List<String> outputContent) {
        try {
            Files.write(new File(outputPath).toPath(), outputContent);
        } catch (IOException e) {
            throw new IllegalArgumentException("A problem occurred during saving the file content.");
        }
    }
}
