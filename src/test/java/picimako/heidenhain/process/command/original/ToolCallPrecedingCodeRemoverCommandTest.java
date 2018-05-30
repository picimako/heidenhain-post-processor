package picimako.heidenhain.process.command.original;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link ToolCallPrecedingCodeRemoverCommand}.
 *
 * @author Tamas Balog
 */
class ToolCallPrecedingCodeRemoverCommandTest {

    private final ToolCallPrecedingCodeRemoverCommand command = new ToolCallPrecedingCodeRemoverCommand();

    @Test
    void shouldRemoveRowIfRowIsNotPastToolCall() {
        PostProcessorContext context = new PostProcessorContext(";AUFMASS=0,500");
        context.setContainToolCall(true);

        assertThat(command.process(context)).isEmpty();
    }

    @Test
    void shouldSetPastToolCallToTrueIfTheCurrentRowStartsWithToolCall() {
        PostProcessorContext context = new PostProcessorContext("TOOLCALL0ZS6000");
        context.setContainToolCall(true);

        assertThat(command.process(context)).isEqualTo("TOOLCALL0ZS6000");
        assertThat(context.isPastToolCall()).isTrue();
    }

    @Test
    void shouldNotRemoveRowIfFileDoesntContainToolCallStatement() {
        PostProcessorContext context = new PostProcessorContext(";AUFMASS=0,500");
        context.setContainToolCall(false);

        assertThat(command.process(context)).isNull();
    }
}
