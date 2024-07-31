package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final Path pathToStylishResultFile = pathNormaliser(
            "src/test/resources/nestedstylishresult.txt");
    private static String stylishCompareFileContent;
    private static final Path pathToPlainResultFile = pathNormaliser(
            "src/test/resources/plainParsingResult.txt");
    private static String plainCompareFileContent;
    private static final Path pathToJsonResultFile = pathNormaliser(
            "src/test/resources/jsonParsingResult.txt");
    private static String asJsonCompareFileContent;

    @BeforeAll
    public static void fileReader() throws Exception {
        stylishCompareFileContent = readString(pathToStylishResultFile);
        plainCompareFileContent = readString(pathToPlainResultFile);
        asJsonCompareFileContent = readString(pathToJsonResultFile);
    }

    @Test
    public void testGenerateNestedJsonFilesDefaultFormat() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json"));
    }

    @Test
    public void testGenerateNestedYamlFilesDefaultFormat() throws Exception {
        assertEquals(plainCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml"));
    }

    @Test
    public void testGenerateNestedJsonStylish() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "stylish"));
    }

    @Test
    public void testGenerateNestedJsonPlain() throws Exception {
        assertEquals(plainCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "plain"));
    }

    @Test
    public void testGenerateNestedJsonAsJson() throws Exception {
        assertEquals(asJsonCompareFileContent,
                Differ.generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "json"));
    }

    @Test
    public void testGenerateNestedYamlStylish() throws Exception {
        assertEquals(stylishCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml",
                        "stylish"));
    }

    @Test
    public void testGenerateNestedYamlPlain() throws Exception {
        assertEquals(plainCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml",
                        "plain"));
    }

    @Test
    public void testGenerateNestedYamlAsJson() throws Exception {
        assertEquals(asJsonCompareFileContent,
                Differ.generate("src/test/resources/file1.yaml",
                        "src/test/resources/file2.yaml",
                        "json"));
    }

    public static Path pathNormaliser(String pathToFile) {
        return Path.of(pathToFile).toAbsolutePath().normalize();
    }
}
