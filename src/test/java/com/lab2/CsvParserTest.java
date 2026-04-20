package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;


import com.lab2.csv_parsing.CsvSplitParser;
import com.lab2.csv_parsing.OpenCsvWriter;

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
@Test
public void testOpenCsvWriter() throws Exception {
    OpenCsvWriter writer = new OpenCsvWriter();
    String path = "target/test_output.csv";
    
    List<Student> students = List.of(
        new Student(1, "Test1", 20, 8.5),
        new Student(2, "Test2", 21, 9.0)
    );
    
    writer.write(path, students);
    assertTrue(Files.exists(Path.of(path)));
}

@Test(expectedExceptions = Exception.class)
public void testParseNonExistingFile() throws Exception {
    new CsvSplitParser().parse(Files.newInputStream(Path.of("non_existing.csv")));
}
}
