package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class Json {

  // Сериализация списка различий в JSON-строку
  public static String format(List<Map<String, Object>> diff) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(diff);
  }
}
