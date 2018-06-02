package picimako.heidenhain.process.command.original;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link M30AdderForRowsPastLM09Command}.
 *
 * @author Tamas Balog
 */
class M30AdderForRowsPastLM09CommandTest {

    private final M30AdderForRowsPastLM09Command command = new M30AdderForRowsPastLM09Command();

    @Test
    void shouldReturnM30IfRowPastLM09AndIsLastOne() {
        PostProcessorContext context = new PostProcessorContext("END PGM teszt13 MM");
        context.setPastLM09(true);

        assertThat(command.process(context)).isEqualTo("M30");
    }

    @Test
    void shouldNotDoReplacementIfRowNotPastLM09() {
        PostProcessorContext context = new PostProcessorContext("END PGM teszt13 MM");

        assertThat(command.process(context)).isNull();
    }

    @Test
    void shouldNotDoReplacementIfRowIsNotTheLastOne() {
        PostProcessorContext context = new PostProcessorContext("CYCL DEF 19.1");
        context.setPastLM09(true);

        assertThat(command.process(context)).isNull();
    }
}
