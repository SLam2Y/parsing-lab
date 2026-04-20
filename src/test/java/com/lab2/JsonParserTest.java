package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

import com.lab2.json_parsing.JsonParser;

public class JsonParserTest {

   @Test
public void testAddStudent() {
    JsonParser parser = new JsonParser();
    String path = "src/main/resources/students.json";

    int before = parser.read(path).size();

    parser.addStudent(path, new Student(999, "Test", 20, 9.5));

    int after = parser.read(path).size();

    assertTrue(after > before);
}
@Test
public void testUpdateStudent() {
    JsonParser parser = new JsonParser();
    String path = "src/main/resources/students.json";

    parser.updateStudentGrade(path, 1, 10.0);

    List<Student> students = parser.read(path);

    boolean updated = students.stream()
        .anyMatch(s -> s.getId() == 1 && s.getGrade() == 10.0);

    assertTrue(updated);
}
}