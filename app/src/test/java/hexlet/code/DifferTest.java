package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {

    private static final Path PATH_TO_STYLISH_RESULT_FILE = pathNormaliser(
            "src/test/resources/stylishParsingResult.txt");
    private static String stylishCompareFileContent;
    private static final Path PATH_TO_PLAIN_RESULT_FILE = pathNormaliser(
            "src/test/resources/plainParsingResult.txt");
    private static String plainCompareFileContent;
    private static final Path PATH_TO_JSON_RESULT_FILE = pathNormaliser(
            "src/test/resources/jsonParsingResult.txt");
    private static String asJsonCompareFileContent;

    @BeforeAll
    public static void fileReader() throws Exception {
        stylishCompareFileContent = readString(PATH_TO_STYLISH_RESULT_FILE);
        plainCompareFileContent = readString(PATH_TO_PLAIN_RESULT_FILE);
        asJsonCompareFileContent = readString(PATH_TO_JSON_RESULT_FILE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public  void testGenerateDefaultFormat(String format) throws Exception {
        String filePath1 = pathNormaliser("src/test/resources/file1." + format).toString();
        String filePath2 = pathNormaliser("src/test/resources/file2." + format).toString();
        assertEquals(stylishCompareFileContent, Differ.generate(filePath1, filePath2));
    }

    @ParameterizedTest
    @CsvSource({
            "json, stylish, stylishCompareFileContent",
            "json, plain, plainCompareFileContent",
            "json, json, asJsonCompareFileContent",
            "yml, stylish, stylishCompareFileContent",
            "yml, plain, plainCompareFileContent",
            "yml, json, asJsonCompareFileContent"
    })
    public  void testGenerateWithFormat(String format, String outputFormat, String expectedContentField) throws Exception {
        String filePath1 = pathNormaliser("src/test/resources/file1." + format).toString();
        String filePath2 = pathNormaliser("src/test/resources/file2." + format).toString();
        String expectedContent = getExpectedContent(expectedContentField);
        assertEquals(expectedContent, Differ.generate(filePath1, filePath2, outputFormat));
    }

    private static String getExpectedContent(String contentField) {
        switch (contentField) {
            case "stylishCompareFileContent":
                return stylishCompareFileContent;
            case "plainCompareFileContent":
                return plainCompareFileContent;
            case "asJsonCompareFileContent":
                return asJsonCompareFileContent;
            default:
                throw new IllegalArgumentException("Unknown content field: " + contentField);
        }
    }

    public static Path pathNormaliser(String pathToFile) {
        return Path.of(pathToFile).toAbsolutePath().normalize();
    }
}
