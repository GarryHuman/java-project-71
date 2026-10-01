package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

  // Вызов по умолчанию с форматом stylish
  public static String generate(String filePath1, String filePath2) throws Exception {
    return generate(filePath1, filePath2, "stylish");
  }

  // Сравнение двух файлов с выбором формата вывода
  public static String generate(String filePath1, String filePath2, String format)
      throws Exception {
    String content1 = readFile(filePath1);
    String content2 = readFile(filePath2);

    String dataFormat1 = getFormat(filePath1);
    String dataFormat2 = getFormat(filePath2);

    // Парсинг входных данных в Map
    Map<String, Object> data1 = Parser.parse(content1, dataFormat1);
    Map<String, Object> data2 = Parser.parse(content2, dataFormat2);

    // Вычисление разницы и форматирование результата
    List<Map<String, Object>> diff = Tree.build(data1, data2);
    return Formatter.format(diff, format);
  }

  // Определение формата по расширению файла
  private static String getFormat(String filePath) {
    int dotIndex = filePath.lastIndexOf('.');
    if (dotIndex == -1 || dotIndex == filePath.length() - 1) {
      throw new IllegalArgumentException("File has no extension: " + filePath);
    }
    return filePath.substring(dotIndex + 1);
  }

  // Чтение содержимого файла
  private static String readFile(String filePath) throws Exception {
    Path path = Paths.get(filePath).toAbsolutePath().normalize();

    if (!Files.exists(path)) {
      throw new IllegalArgumentException("File '" + path + "' does not exist");
    }
    return Files.readString(path);
  }
}
