package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Tree {

  // Построение структуры различий между двумя наборами данных
  public static List<Map<String, Object>> build(
      Map<String, Object> data1, Map<String, Object> data2) {
    // Сбор и сортировка всех ключей в алфавитном порядке
    Set<String> keys = new TreeSet<>(data1.keySet());
    keys.addAll(data2.keySet());

    List<Map<String, Object>> diff = new ArrayList<>();

    for (String key : keys) {
      Map<String, Object> node = new LinkedHashMap<>();
      node.put("key", key);

      // Определение состояния ключа
      if (!data1.containsKey(key)) {
        node.put("type", "added");
        node.put("value", data2.get(key));
      } else if (!data2.containsKey(key)) {
        node.put("type", "deleted");
        node.put("value", data1.get(key));
      } else if (Objects.equals(data1.get(key), data2.get(key))) {
        node.put("type", "unchanged");
        node.put("value", data1.get(key));
      } else {
        node.put("type", "changed");
        node.put("oldValue", data1.get(key));
        node.put("newValue", data2.get(key));
      }

      diff.add(node);
    }

    return diff;
  }
}
