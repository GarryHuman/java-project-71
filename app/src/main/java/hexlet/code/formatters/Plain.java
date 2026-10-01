package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {

  // Преобразование списка различий в формат plain
  public static String format(List<Map<String, Object>> diff) {
    List<String> lines = new ArrayList<>();

    for (Map<String, Object> node : diff) {
      String key = (String) node.get("key");
      String type = (String) node.get("type");

      switch (type) {
        case "deleted" -> lines.add(String.format("Property '%s' was removed", key));
        case "added" ->
            lines.add(
                String.format(
                    "Property '%s' was added with value: %s", key, stringify(node.get("value"))));
        case "changed" ->
            lines.add(
                String.format(
                    "Property '%s' was updated. From %s to %s",
                    key, stringify(node.get("oldValue")), stringify(node.get("newValue"))));
        case "unchanged" -> {
          // Неизмененные свойства пропускаются
        }
        default -> throw new IllegalArgumentException("Unknown node type: " + type);
      }
    }

    return String.join("\n", lines);
  }

  // Приведение значения к представлению для формата plain
  private static String stringify(Object value) {
    if (value == null) {
      return "null";
    }
    if (value instanceof Collection<?>
        || value instanceof Map<?, ?>
        || value.getClass().isArray()) {
      return "[complex value]";
    }
    if (value instanceof String) {
      return "'" + value + "'";
    }
    return String.valueOf(value);
  }
}
