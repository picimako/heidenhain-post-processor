package picimako.heidenhain.process.command.original;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link LRowNotEndingR0FMaxLToG1ReplacerCommand}.
 *
 * @author Tamas Balog
 */
class LRowNotEndingR0FMaxLToG1ReplacerCommandTest {
    private final LRowNotEndingR0FMaxLToG1ReplacerCommand command = new LRowNotEndingR0FMaxLToG1ReplacerCommand();

    @Test
    void shouldReplaceR0FMaxToG1AtTheEndOfRowIfRowDoesntStartWithL() {
        PostProcessorContext context = new PostProcessorContext("LX-91,730Y-50,001R0FQ3");

        assertThat(command.process(context)).isEqualTo("G1X-91,730Y-50,001R0FQ3");
    }

    @Test
    void shouldNotDoReplacementIfRowDoesntStartWithL() {
        PostProcessorContext context = new PostProcessorContext("CYCLDEF19.0BEARBEITUNGSEBENE");

        assertThat(command.process(context)).isNull();
    }

    @Test
    void shouldNotDoReplacementIfRowEndsWithR0FMax() {
        PostProcessorContext context = new PostProcessorContext("LX-38,944Z63,895B-30.77C0.00R0FMAX");

        assertThat(command.process(context)).isNull();
    }
}
