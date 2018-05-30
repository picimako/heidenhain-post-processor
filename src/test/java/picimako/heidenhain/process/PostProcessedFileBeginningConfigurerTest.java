package picimako.heidenhain.process;

/**
 * Unit test for {@link PostProcessedFileBeginningConfigurer}.
 *
 * @author Tamas Balog
 */
class PostProcessedFileBeginningConfigurerTest {

    private PostProcessedFileBeginningConfigurer configurer;

    //FIXME: need to mock TextArea

//    @Test
//    void shouldAddInBracesCommentOnly() {
//        List<String> outputContent = new ArrayList<>();
//        setupContextAndConfigurer("in braces", "");
//
//        configurer.configure(outputContent);
//
//        assertThat(outputContent).containsExactly("(in braces)");
//    }
//
//    @Test
//    void shouldNotAddInBracesCommentIfItIsEmpty() {
//        List<String> outputContent = new ArrayList<>();
//        setupContextAndConfigurer("", "");
//
//        configurer.configure(outputContent);
//
//        assertThat(outputContent).isEmpty();
//    }
//
//    @Test
//    void shouldAddWithoutBracesPrepareCommands() {
//        List<String> outputContent = new ArrayList<>();
//        setupContextAndConfigurer("", "without braces");
//
//        configurer.configure(outputContent);
//
//        assertThat(outputContent).containsExactly("without braces");
//    }
//
//    private void setupContextAndConfigurer(String inBraces, String withoutBraces) {
//        PostProcessorContext context = new PostProcessorContext();
//        context.setInBracesCommentTextArea(inBraces);
//        context.setWithoutBracesPrepareCommandsTextArea(withoutBraces);
//        configurer = new PostProcessedFileBeginningConfigurer(context);
//    }
}
