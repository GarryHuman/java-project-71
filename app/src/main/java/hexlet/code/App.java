package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    synopsisHeading = "%nUsage: ",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Сравнивает два файла конфигурации и показывает различия.")
public class App implements Callable<Integer> {

  @Parameters(index = "0", paramLabel = "filepath1", description = "Путь к первому файлу")
  private String filepath1;

  @Parameters(index = "1", paramLabel = "filepath2", description = "Путь ко второму файлу")
  private String filepath2;

  @Option(
      names = {"-f", "--format"},
      paramLabel = "format",
      defaultValue = "stylish",
      description = "output format [default: stylish]")
  private String format;

  @Override
  public Integer call() throws Exception {
    // Вызов генератора с переданным форматом и вывод результата
    String diff = Differ.generate(filepath1, filepath2, format);
    System.out.println(diff);
    return 0;
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
