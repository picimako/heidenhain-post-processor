package picimako.heidenhain.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.command.original.LRowEndingR0FMaxToG0ReplacerCommand;

/**
 * Unit test for {@link LRowEndingR0FMaxToG0ReplacerCommand}.
 *
 * @author Tamas Balog
 */
class LRowEndingR0FMaxToG0ReplacerCommandTest {

    private final Command command = new LRowEndingR0FMaxToG0ReplacerCommand();

    @Test
    void shouldReplaceR0FMaxToG0IfRowStartsWithLAndEndsWithR0FMax() {
        PostProcessorContext context = new PostProcessorContext("LX-50,198Z59,403B-25.00C0.00R0FMAX");

        assertThat(command.process(context)).isEqualTo("X-50,198Z59,403B-25.00C0.00G0");
    }

    @Test
    void shouldNotDoReplacementIfRowDoesntStartWithL() {
        PostProcessorContext context = new PostProcessorContext(";DM=8,000-ECKR=4,000");

        assertThat(command.process(context)).isNull();
    }

    @Test
    void shouldNotDoReplacementIfRowDoesntEndWithR0FMax() {
        PostProcessorContext context = new PostProcessorContext("LX-7,350Z40,817B-33.29C0.02");

        assertThat(command.process(context)).isNull();
    }
}
