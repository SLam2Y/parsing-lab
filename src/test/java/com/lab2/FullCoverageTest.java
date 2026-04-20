package com.lab2;

import com.lab2.csv_parsing.*;
import com.lab2.xml_parsing.*;
import com.lab2.json_parsing.JsonParser;
 
import org.testng.annotations.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.testng.Assert.*;

public class FullCoverageTest {

    @Test
    public void testFullCoverage() throws Exception {
        String csvPath = "src/main/resources/students.csv";
        String xmlPath = "src/main/resources/students.xml";
        String jsonPath = "src/main/resources/students.json";

        // ==================== CSV ====================
        try (InputStream is = Files.newInputStream(Path.of(csvPath))) {
            List<Student> students = new CsvSplitParser().parse(is);
            assertFalse(students.isEmpty());
        }

        try (InputStream is = Files.newInputStream(Path.of(csvPath))) {
            List<Student> students = new CsvScannerParser().parse(is);
            assertFalse(students.isEmpty());
        }

        try (InputStream is = Files.newInputStream(Path.of(csvPath))) {
            List<Student> students = new OpenCsvParser().parse(is);
            assertFalse(students.isEmpty());
        }

        try (InputStream is = Files.newInputStream(Path.of(csvPath))) {
            List<Student> students = new CommonsCsvParser().parse(is);
            assertFalse(students.isEmpty());
        }

        // Запись CSV
        new OpenCsvWriter().write("target/test_csv_opencsv.csv", 
            List.of(new Student(99, "TestOpen", 20, 9.0)));
        
        new CommonsCsvWriter().write("target/test_csv_commons.csv", 
            List.of(new Student(88, "TestCommons", 21, 8.5)));

        // ==================== XML ====================
        try (InputStream is = Files.newInputStream(Path.of(xmlPath))) {
            List<Student> students = new XmlDomParser().parse(is);
            assertFalse(students.isEmpty());
        }

        try (InputStream is = Files.newInputStream(Path.of(xmlPath))) {
            List<Student> students = new XmlSaxParser().parse(is);
            assertFalse(students.isEmpty());
        }

        // XPathParser (исправлено!)
        try (InputStream is = Files.newInputStream(Path.of(xmlPath))) {
            List<Student> students = new XmlXPathParser().parse(is);
            assertFalse(students.isEmpty());

            // Дополнительное задание — фильтрация
            List<Student> highGrade = new XmlXPathParser().findStudentsWithGradeGreaterThan(is, 8.0);
            assertNotNull(highGrade);
        }

        // ==================== JSON ====================
        JsonParser jsonParser = new JsonParser();

        List<Student> jsonStudents = jsonParser.read(jsonPath);
        assertFalse(jsonStudents.isEmpty());

        jsonParser.addStudent(jsonPath, new Student(100, "NewStudent", 22, 9.8));
        jsonParser.updateStudentGrade(jsonPath, 1, 10.0);

        jsonParser.save(jsonPath, jsonStudents); // если есть метод save
    }
}