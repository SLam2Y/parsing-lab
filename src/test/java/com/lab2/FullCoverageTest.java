package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.lab2.csv_parsing.CsvSplitParser;
import com.lab2.json_parsing.JsonParser;
import com.lab2.xml_parsing.XmlDomParser;

public class FullCoverageTest {

    @Test
    public void testEverything() throws Exception {

        // CSV
        CsvSplitParser csvParser = new CsvSplitParser();
        try (InputStream is = Files.newInputStream(
                Path.of("src/main/resources/students.csv"))) {

            List<Student> csvStudents = csvParser.parse(is);
            assertFalse(csvStudents.isEmpty());

            StudentStatistics.getAverage(csvStudents);
        }

        // XML
        XmlDomParser xmlParser = new XmlDomParser();
        try (InputStream is = Files.newInputStream(
                Path.of("src/main/resources/students.xml"))) {

            List<Student> xmlStudents = xmlParser.parse(is);
            assertFalse(xmlStudents.isEmpty());
        }

        // JSON
        JsonParser jsonParser = new JsonParser();
        String path = "src/main/resources/students.json";

        List<Student> jsonStudents = jsonParser.read(path);
        assertFalse(jsonStudents.isEmpty());

        jsonParser.addStudent(path, new Student(999, "Test", 20, 9.5));
        jsonParser.updateStudentGrade(path, 1, 10.0);
        jsonParser.save(path, jsonStudents);
    }
}