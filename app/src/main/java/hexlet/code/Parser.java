package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {

    public static Map<String, Object> parse(String content, String format) throws Exception {
        if ("json".equals(format)) {
            return parseJson(content);
        } else if ("yaml".equals(format) || "yml".equals(format)) {
            return parseYaml(content);
        } else {
            throw new RuntimeException("Unknown format of file input" + format);
        }
    }

    public static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        return yamlMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
