package hexlet.code;


import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String fileFormat1 = getFileType(filepath1);
        String fileFormat2 = getFileType(filepath2);

        Map<String,Object> file1 = Parser.parse(content1, fileFormat1);
        Map<String,Object> file2 = Parser.parse(content2, fileFormat2);

        List<Map<String,Object>> compareResult = Comparator.compare(file1,file2);
        return StyleFormat.format(compareResult, format);
    }
    private static String readFile(String filePath) throws Exception  {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    private static String getFileType(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }
}
