package picimako.heidenhain.app;

import static java.util.stream.Collectors.toMap;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

/**
 * Reads the settings.properties file into a map.
 *
 * @author Tamas Balog
 */
final class SettingsFileReader {
    private static final String SETTINGS_ENTRY_DELIMITER = "=";

    Map<String, String> read(String settingsFilePath) {
        try {
            File file = new File(settingsFilePath);
            return Files.readAllLines(file.toPath())
                .stream()
                .map(s -> s.split(SETTINGS_ENTRY_DELIMITER))
                //TODO: beautify this
                .collect(toMap(e -> e[0], e -> {
                    String value = EMPTY_STRING;
                    if (e.length == 2) {
                        value = e[1];
                    }
                    return value;
                }));
        } catch (Exception e) {
            throw new RuntimeException("Valami félrement a settings.properties beolvasásakor.", e);
        }
    }
}
