package com.lab2.json_parsing;

import com.lab2.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON parser using json.simple library.
 */
public class JsonParser {

    public List<Student> read(String path) {

        List<Student> students = new ArrayList<>();

        try {

            JSONParser parser = new JSONParser();

            JSONObject root = (JSONObject) parser.parse(new FileReader(path));

            JSONArray array = (JSONArray) root.get("students");

            for (Object obj : array) {

                JSONObject s = (JSONObject) obj;

                int id = ((Long) s.get("id")).intValue();
                String name = (String) s.get("name");
                int age = ((Long) s.get("age")).intValue();
                double grade = (Double) s.get("grade");

                students.add(new Student(id, name, age, grade));
            }

        } catch (Exception e) {
            System.out.println("Invalid JSON: " + e.getMessage());
        }

        return students;
    }

    public void addStudent(String path, Student newStudent) {

        try {

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(path));

            JSONArray array = (JSONArray) root.get("students");

            JSONObject obj = new JSONObject();

            obj.put("id", newStudent.getId());
            obj.put("name", newStudent.getName());
            obj.put("age", newStudent.getAge());
            obj.put("grade", newStudent.getGrade());

            array.add(obj);

            FileWriter writer = new FileWriter(path);
            writer.write(root.toJSONString());
            writer.close();

        } catch (Exception e) {
            System.out.println("JSON error: " + e.getMessage());
        }
    }

    public void updateStudentGrade(String path, int studentId, double newGrade) {

        try {

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new FileReader(path));

            JSONArray array = (JSONArray) root.get("students");

            for (Object obj : array) {

                JSONObject student = (JSONObject) obj;

                int id = ((Long) student.get("id")).intValue();

                if (id == studentId) {
                    student.put("grade", newGrade);
                }
            }

            FileWriter writer = new FileWriter(path);
            writer.write(root.toJSONString());
            writer.close();

        } catch (Exception e) {
            System.out.println("JSON update error: " + e.getMessage());
        }
    }

    public void save(String path, List<Student> students) {

        JSONObject root = new JSONObject();
        JSONArray array = new JSONArray();

        for (Student s : students) {

            JSONObject obj = new JSONObject();

            obj.put("id", s.getId());
            obj.put("name", s.getName());
            obj.put("age", s.getAge());
            obj.put("grade", s.getGrade());

            array.add(obj);
        }

        root.put("students", array);

        try {

            FileWriter writer = new FileWriter(path);
            writer.write(root.toJSONString());
            writer.close();

        } catch (Exception e) {
            System.out.println("JSON save error: " + e.getMessage());
        }
    }
}