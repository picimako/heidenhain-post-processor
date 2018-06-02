package picimako.heidenhain.process.command.original;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link RowPastLM09ToEmptyStringReplacerCommand}.
 *
 * @author Tamas Balog
 */
class RowPastLM09ToEmptyStringReplacerCommandTest {

    private final RowPastLM09ToEmptyStringReplacerCommand command = new RowPastLM09ToEmptyStringReplacerCommand();

    @Test
    void shouldReturnEmptyStringIfRowMatchesLM09() {
        PostProcessorContext context = new PostProcessorContext(" L M09");

        assertThat(command.process(context)).isEqualTo("");
    }

    @Test
    void shouldReturnEmptyStringIfRowPastLM09() {
        PostProcessorContext context = new PostProcessorContext("L M129");
        context.setPastLM09(true);

        assertThat(command.process(context)).isEqualTo("");
        assertThat(context.isPastLM09()).isTrue();
    }

    @Test
    void shouldNotDoReplacementIfRowDoesntMatchLM09AndIsNotPastLM09() {
        PostProcessorContext context = new PostProcessorContext("LX-2,652Y49,986Z59,036B-48.22C-0.01R0FMAX");

        assertThat(command.process(context)).isNull();
    }
}
