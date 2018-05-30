package picimako.heidenhain.process;

import static picimako.heidenhain.app.HeidenhainFileReader.read;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Executes the post-processing.
 *
 * @author Tamas Balog
 */
public final class PostProcessor {
    private static final Pattern ROW_WITH_OPTIONAL_ROW_INDEX_PATTERN = Pattern.compile("^(?:\\d+ )?(?<command>.*)$");

    private final PrePostProcessingContextUpdater prePostProcessingContextUpdater = new PrePostProcessingContextUpdater();
    private final PostProcessedFileBeginningConfigurer fileBeginningConfigurer;
    private final PostProcessorContext context;

    public PostProcessor(PostProcessedFileBeginningConfigurer fileBeginningConfigurer, PostProcessorContext context) {
        this.fileBeginningConfigurer = fileBeginningConfigurer;
        this.context = context;
    }

    /**
     * Executes post-processing for the argument file content.
     *
     * @param file              the list of row contents
     * @param midResultFilePath a temporary file to store a mid result from which result dependent post-processing can be done
     * @param outputPath        the file path to save the processed content to
     */
    public void execute(List<String> file, String midResultFilePath, String outputPath) {
        PostProcessedFileWriter postProcessedFileWriter = new PostProcessedFileWriter();
        postProcessedFileWriter.savePostProcessedContentToFile(midResultFilePath, doPostProcessing(file));
        postProcessedFileWriter.savePostProcessedContentToFile(outputPath, doPostProcessingSecondRound(read(midResultFilePath)));
    }

    private List<String> doPostProcessing(List<String> file) {
        List<String> outputContent = new LinkedList<>();
        fileBeginningConfigurer.configure(outputContent);
        return postProcess(file, outputContent, PostProcessorConfiguration.get(), ROW_PATTERN);
    }

    private List<String> doPostProcessingSecondRound(List<String> midResultFile) {
        List<String> outputContent = new LinkedList<>();
        return postProcess(midResultFile, outputContent, ResultDependentConfiguration.get());
    }

    /**
     * Line indexing is not relevant, doesn't effect the program execution.
     */
    private List<String> postProcess(List<String> file, List<String> outputContent, List<Command> config) {
        Command command = new ChainedCommand(config);
        for (String row : file) {
            Matcher matcher = ROW_WITH_OPTIONAL_ROW_INDEX_PATTERN.matcher(row);
            if (matcher.matches()) {
                context.setRow(matcher.group("command"));
                String processed = command.process(context);
                if (!processed.isEmpty()) { //To be able to work with removal commands.
                    outputContent.add(processed);
                }
            } else {
                //TODO: update the error message
                throw new IllegalArgumentException("The row doesn't match the expected format: " + row + "\nFormat must be [<row index> <command>].");
            }
        }
        return outputContent;
    }
}
