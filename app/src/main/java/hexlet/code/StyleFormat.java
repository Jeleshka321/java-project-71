package hexlet.code;


import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class StyleFormat {
    // transforms the result into the required format
    public static String format( List<Map<String, Object>> compareResult, String format)  {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(compareResult);
            case "plain" -> PlainFormatter.format(compareResult);
            case "json" -> JsonFormatter.format(compareResult);
            default -> throw new IllegalArgumentException("Unknown data extension:" + format);
        };
    }

}