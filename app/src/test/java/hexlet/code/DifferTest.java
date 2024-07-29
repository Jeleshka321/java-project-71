package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String stylishOutput;
    private static String plainOutput;
    private static String jsonOutput;
    @BeforeAll
    public static void setExpected() throws IOException {
        stylishOutput = getOutput("src/test/resources/testStylishOutput.txt");
        plainOutput = getOutput("src/test/resources/testPlainOutput.txt");
        jsonOutput = getOutput("src/test/resources/resultForTest.json");
    }

    public static String getOutput(String path) throws IOException {
        var absolutePath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(absolutePath).trim();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGenerateStylishWithJsonOrYamlInput(String fileExtension) throws Exception {
        var str1 = "src/test/resources/file1." + fileExtension;
        var str2 = "src/test/resources/file2." + fileExtension;
        var format = "stylish";
        var expected = stylishOutput;

        var actual = generate(str1, str2, format);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGeneratePlainWithJsonOrYamlInput(String fileExtension) throws Exception {
        var str1 = "src/test/resources/file1." + fileExtension;
        var str2 = "src/test/resources/file2." + fileExtension;
        var format = "plain";
        var expected = plainOutput;

        var actual = generate(str1, str2, format);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGenerateJsonWithJsonOrYamlInput(String fileExtension) throws Exception {
        var str1 = "src/test/resources/file1." + fileExtension;
        var str2 = "src/test/resources/file2." + fileExtension;
        var format = "json";
        var expected = jsonOutput;

        var actual = generate(str1, str2, format);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testGenerateDefaultWithJsonOrYamlInput(String fileExtension) throws Exception {
        var str1 = "src/test/resources/file1." + fileExtension;
        var str2 = "src/test/resources/file2." + fileExtension;
        var expected = stylishOutput;

        var actual = generate(str1, str2);

        assertEquals(expected, actual);
    }

}
