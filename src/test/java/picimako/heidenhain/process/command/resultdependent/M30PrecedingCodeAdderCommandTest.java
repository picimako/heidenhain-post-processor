package picimako.heidenhain.process.command.resultdependent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link M30PrecedingCodeAdderCommand}.
 *
 * @author Tamas Balog
 */
class M30PrecedingCodeAdderCommandTest {

    private final M30PrecedingCodeAdderCommand command = new M30PrecedingCodeAdderCommand();

    @Test
    void shouldAddCodeBeforeM30WithNoLeadingOrSucceedingNewLineCharacters() {
        PostProcessorContext context = new PostProcessorContext("M30");
        context.setM30PrecedingCodeText("LX0,741Z65,332B-50.00C0.00R0FMAX\n21471 LZ66,899B-50.00C0.00R0FMAX");
        String expectedResult = "LX0,741Z65,332B-50.00C0.00R0FMAX\n21471 LZ66,899B-50.00C0.00R0FMAX\nM30";

        assertThat(command.process(context)).isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnM30IfTheCurrentRowIsM30ButThePrecedingCodeIsEmpty() {
        PostProcessorContext context = new PostProcessorContext("M30");
        context.setM30PrecedingCodeText("");

        assertThat(command.process(context)).isEqualTo("M30");
    }

    @Test
    void shouldNotAddCodeIfCurrentRowIsNotM30() {
        PostProcessorContext context = new PostProcessorContext("Not M30");

        assertThat(command.process(context)).isNull();
    }
}
