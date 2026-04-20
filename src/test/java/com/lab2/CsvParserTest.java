package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;


import com.lab2.csv_parsing.CsvSplitParser;

public class CsvParserTest {
 @Test
public void testCsvParsing() throws Exception {
    CsvSplitParser parser = new CsvSplitParser();

    InputStream is = Files.newInputStream(
        Path.of("src/main/resources/students.csv")
    );

    List<Student> students = parser.parse(is);

    assertNotNull(students);
    assertTrue(students.size() > 0);
}
@Test
public void testCsvNotEmpty() throws Exception {
    CsvSplitParser parser = new CsvSplitParser();

    try (InputStream is = Files.newInputStream(
            Path.of("src/main/resources/students.csv"))) {

        List<Student> students = parser.parse(is);

        assertFalse(students.isEmpty());
    }
}
}
