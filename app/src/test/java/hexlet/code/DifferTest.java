package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {

  private static String expectedStylish;
  private static String expectedPlain;
  private static String expectedJson;

  // Путь к файлу в src/test/resources
  private static Path getFixturePath(String fileName) {
    return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
  }

  // Чтение содержимого фикстуры с нормализацией строк
  private static String readFixture(String fileName) throws Exception {
    return Files.readString(getFixturePath(fileName)).trim().replace("\r\n", "\n");
  }

  @BeforeAll
  static void setUp() throws Exception {
    expectedStylish = readFixture("expected_stylish.txt");
    expectedPlain = readFixture("expected_plain.txt");
    expectedJson = readFixture("expected_json.json");
  }

  // Проверка формата stylish по умолчанию
  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  void testGenerateDiffDefault(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2);

    assertThat(actual.trim().replace("\r\n", "\n")).isEqualTo(expectedStylish);
  }

  // Проверка явного вызова формата stylish
  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  void testGenerateDiffStylish(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2, "stylish");

    assertThat(actual.trim().replace("\r\n", "\n")).isEqualTo(expectedStylish);
  }

  // Проверка вызова формата plain
  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  void testGenerateDiffPlain(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2, "plain");

    assertThat(actual.trim().replace("\r\n", "\n")).isEqualTo(expectedPlain);
  }

  // Проверка вызова формата json
  @ParameterizedTest
  @ValueSource(strings = {"json", "yml"})
  void testGenerateDiffJson(String format) throws Exception {
    String path1 = getFixturePath("file1." + format).toString();
    String path2 = getFixturePath("file2." + format).toString();

    String actual = Differ.generate(path1, path2, "json");

    assertThat(actual.trim().replace("\r\n", "\n")).isEqualTo(expectedJson);
  }
}
