package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: stylish]", paramLabel = "format")
    private String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested = false;

    @Parameters(index = "0", description = "path to first file",
            paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file",
            paramLabel = "filepath2")
    private String filepath2;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }
}




