package com.lab2;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;


import com.lab2.xml_parsing.XmlDomParser;
import com.lab2.xml_parsing.XmlXPathParser;

public class XmlParserTest {

 @Test
public void testXmlDomParsing() throws Exception {
    XmlDomParser parser = new XmlDomParser();

    try (InputStream is = Files.newInputStream(
            Path.of("src/main/resources/students.xml"))) {

        List<Student> students = parser.parse(is);

        assertNotNull(students);
        assertTrue(students.size() > 0);
    }
}
@Test
public void testXmlCount() throws Exception {
    XmlDomParser parser = new XmlDomParser();

    try (InputStream is = Files.newInputStream(
            Path.of("src/main/resources/students.xml"))) {

        List<Student> students = parser.parse(is);

        assertFalse(students.isEmpty());
    }

}
@Test
public void testXPathFilter() throws Exception {
    XmlXPathParser parser = new XmlXPathParser();
    try (InputStream is = Files.newInputStream(Path.of("src/main/resources/students.xml"))) {
        List<Student> high = parser.findStudentsWithGradeGreaterThan(is, 8.5);
        assertNotNull(high);
    }
}
}