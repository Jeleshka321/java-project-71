package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        result.forEach(element -> {
            switch (element.get("status").toString()) {
                case "added" -> sb.append("  + ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("value"))
                        .append("\n");
                case "removed" -> sb.append("  - ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("value"))
                        .append("\n");
                case "unchanged" -> sb.append("    ")
                        .append(element.get("key"))
                        .append(": ")
                        .append(element.get("value"))
                        .append("\n");
                case "updated" -> {
                    sb.append("  - ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(element.get("oldValue"))
                            .append("\n");
                    sb.append("  + ")
                            .append(element.get("key"))
                            .append(": ")
                            .append(element.get("newValue"))
                            .append("\n");
                }
                default -> throw new RuntimeException("Unknown status");
            }
        });

        sb.append("}");

        return sb.toString().trim();
    }
}