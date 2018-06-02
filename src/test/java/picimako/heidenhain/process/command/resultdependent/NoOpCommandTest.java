package picimako.heidenhain.process.command.resultdependent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * Unit test for {@link NoOpCommand}.
 *
 * @author Tamas Balog
 */
class NoOpCommandTest {

    private final Command command = new NoOpCommand();

    @Test
    void shouldReturnTheCurrentRowFromContext() {
        PostProcessorContext context = new PostProcessorContext("The current row.");

        assertThat(command.process(context)).isEqualTo("The current row.");
    }
}
