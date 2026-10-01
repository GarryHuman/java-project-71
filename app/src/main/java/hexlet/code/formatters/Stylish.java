package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

  // Преобразование структуры различий в формат stylish
  public static String format(List<Map<String, Object>> diff) {
    StringBuilder result = new StringBuilder("{\n");

    for (Map<String, Object> node : diff) {
      String key = (String) node.get("key");
      String type = (String) node.get("type");

      // Формирование строки по типу изменения
      switch (type) {
        case "deleted" -> result.append(String.format("  - %s: %s\n", key, node.get("value")));
        case "added" -> result.append(String.format("  + %s: %s\n", key, node.get("value")));
        case "unchanged" -> result.append(String.format("    %s: %s\n", key, node.get("value")));
        case "changed" -> {
          result.append(String.format("  - %s: %s\n", key, node.get("oldValue")));
          result.append(String.format("  + %s: %s\n", key, node.get("newValue")));
        }
        default -> throw new IllegalArgumentException("Unknown node type: " + type);
      }
    }

    result.append("}");
    return result.toString();
  }
}
