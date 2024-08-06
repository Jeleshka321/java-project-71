package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> result) {
        StringBuilder plainFormat = new StringBuilder();

        result.forEach(element -> {
            switch (element.get("status").toString()) {
                case "added" -> plainFormat.append(String.format("Property '%s' was added with value: %s%n",
                        element.get("key"),
                        replaceWithComplexValue(element.get("value"))));
                case "removed" -> plainFormat.append(String.format("Property '%s' was removed%n",
                        element.get("key")));
                case "updated" -> plainFormat.append(String.format("Property '%s' was updated. From %s to %s%n",
                        element.get("key"),
                        replaceWithComplexValue(element.get("oldValue")),
                        replaceWithComplexValue(element.get("newValue"))));
                case "unchanged" -> {
                    break;
                }
                default -> throw new RuntimeException("Unknown format of output: " + element);
            }
        });

        return plainFormat.toString().trim();
    }

    public static Object replaceWithComplexValue(Object obj) {
        if (obj == null || obj.equals("null")) {
            return null;
        } else if (obj instanceof List
                || obj instanceof Map) {
            return "[complex value]";
        } else if (obj instanceof String) {
            return "'" + obj + "'";
        }

        return obj;
    }
}
