package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class StyleFormat {
    // transforms the result into the required format
    public static String format(String format, List<Map<String, Object>> difference)
            throws JsonProcessingException {

        String result = switch (format) {
            case "stylish" -> StylishFormatter.format(difference);
            case "plain" -> PlainFormatter.format(difference);
            case "json" -> JsonFormatter.format(difference);
            default -> throw new RuntimeException("Unknown format of output");
        };

        return result;
    }
}