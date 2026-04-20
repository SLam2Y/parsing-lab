package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;



public class StudentStatisticsTest {
  @Test
public void testAverage() {
    List<Student> students = List.of(
        new Student(1, "A", 20, 8.0),
        new Student(2, "B", 21, 10.0)
    );

    StudentStatistics stats = new StudentStatistics();

    double avg = StudentStatistics.getAverage(students);

    assertEquals(avg, 9.0);
}
}
