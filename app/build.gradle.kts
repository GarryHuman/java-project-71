plugins {
    // Поддержка сборки исполняемого CLI-приложения
    application
    // Проверка обновлений зависимостей
    id("com.github.ben-manes.versions") version "0.51.0"
    // Линтер и форматирование кода
    id("com.diffplug.spotless") version "6.25.0"
    // Анализ тестового покрытия
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Тестовый фреймворк JUnit 5
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Библиотека утверждений AssertJ
    testImplementation("org.assertj:assertj-core:3.26.3")

    // Утилиты Guava
    implementation(libs.guava)
    // Парсер аргументов командной строки Picocli
    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor("info.picocli:picocli-codegen:4.7.6")
    // Парсинг JSON и YAML через Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
}

java {
    // Версия JDK для сборки
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Точка входа в консольное приложение
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
    // Автоматическая генерация отчета после прогона тестов
    finalizedBy(tasks.jacocoTestReport)
}

// Настройка стиля Google Java Format
spotless {
    java {
        googleJavaFormat()
    }
}

// Версия JaCoCo
jacoco {
    toolVersion = "0.8.12"
}

// Генерация отчетов покрытия в XML и HTML
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

// Проверка минимального порога покрытия (80%) без учета класса точки входа
tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude("hexlet/code/App.class")
        }
    )
}

// Запуск проверки покрытия при выполнении задачи check (и build)
tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}