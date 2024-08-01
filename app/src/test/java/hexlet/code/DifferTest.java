package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Files.readString;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

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

    @Test
    public void testGenerateJsonFilesDefaultFormat() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json"));
    }

    @Test
    public void testGenerateYamlFilesDefaultFormat() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file2.yml"));
    }

    @Test
    public void testGenerateJsonStylish() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "stylish"));
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        assertEquals(plainCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "plain"));
    }

    @Test
    public void testGenerateJsonAsJson() throws Exception {
        assertEquals(asJsonCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "json"));
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file2.yml",
                        "stylish"));
    }

    @Test
    public void testGenerateYamlPlain() throws Exception {
        assertEquals(plainCompareFileContent,
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file2.yml",
                        "plain"));
    }

    @Test
    public void testGenerateYamlAsJson() throws Exception {
        assertEquals(asJsonCompareFileContent,
                Differ.generate("src/test/resources/file1.yml",
                        "src/test/resources/file2.yml",
                        "json"));
    }

    public static Path pathNormaliser(String pathToFile) {
        return Path.of(pathToFile).toAbsolutePath().normalize();
    }
}
