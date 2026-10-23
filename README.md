### Checks:
[![Actions Status](https://github.com/GarryHuman/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/GarryHuman/java-project-71/actions)
[![build](https://github.com/GarryHuman/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/GarryHuman/java-project-71/actions/workflows/build.yml)
## Вычислитель отличий (Gendiff)

## Описание проекта
Консольная утилита для сравнения двух файлов конфигураций. Программа находит различия и показывает, что было добавлено, удалено или изменено.

Поддерживает форматы JSON и YAML.

---

## Требования
- Java 21+
- Make

---

## Установка и запуск

1. Склонируйте репозиторий:
```bash
git clone https://github.com/GarryHuman/java-project-71.git
cd java-project-71
```

2. Соберите проект:
```bash
make build
```

3. Запустите утилиту:
```bash
./build/install/app/bin/app path/to/file1.json path/to/file2.json
```

---

## Примеры использования

### Справка
```bash
./build/install/app/bin/app -h
```

### Форматы вывода

По умолчанию используется формат `stylish`:
```bash
./build/install/app/bin/app file1.json file2.json
```

Вывод в плоском виде (`plain`):
```bash
./build/install/app/bin/app -f plain file1.json file2.json
```

Вывод в формате `json`:
```bash
./build/install/app/bin/app -f json file1.json file2.json
```

---

## Демонстрации

### Сравнение плоских файлов
![Сравнение плоских файлов.](app/assets/compare_json_demo.gif)

### Сравнение вложенных файлов
![Сравнение вложенных файлов.](app/assets/compare_yml_demo.gif)

### Формат plain
![Формат plain.](app/assets/f_plain_demo.gif)

### Формат json
![Формат json.](app/assets/f_json_demo.gif)