package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathToTests = getPath(args[0]);
        Path pathToValues = getPath(args[1]);
        String testsJson = String.join("", Files.readAllLines(pathToTests));
        String valuesJson = String.join("", Files.readAllLines(pathToValues));

        ObjectMapper om = new ObjectMapper();

        Map<String, List<Map<String, Object>>> testsMap = om.readValue(testsJson,
                new TypeReference<Map<String, List<Map<String, Object>>>>() { });
        List<Map<String, Object>> tests = testsMap.get("tests");

        Map<String, List<Map<String, Object>>> valuesMap = om.readValue(valuesJson,
                new TypeReference<Map<String, List<Map<String, Object>>>>() { });
        List<Map<String, Object>> values = valuesMap.get("values");

        List<Map<String, Object>> result = new ArrayList<>(tests);
        for (Map<String, Object> test : result) {
            insertValue(test, values);
        }

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        ObjectWriter writer = om.writer(prettyPrinter);
        writer.writeValue(getPath("src/main/resources/report.json").toFile(), result);

    }

    public static void insertValue(Map<String, Object> test, List<Map<String, Object>> values) {
        for (Map<String, Object> value : values) {
            if ((int) test.get("id") == (int) value.get("id")) {
                test.put("value", value.get("value"));
            }
            if (test.containsKey("values")) {
                List<Map<String, Object>> innerList = (List<Map<String, Object>>) test.get("values");
                for (Map<String, Object> innerMap : innerList) {
                    insertValue(innerMap, values);
                }
            }
        }
    }

    public static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

}
