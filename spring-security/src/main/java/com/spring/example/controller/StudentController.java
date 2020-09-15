package com.spring.example.controller;

import com.spring.example.model.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Tom"),
            new Student(2, "Pete")
    );

    @GetMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Student getStudent(@PathVariable("studentId") Integer studentId) throws IllegalAccessException {
        return STUDENT_LIST.stream().filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalAccessException("Student " + studentId + " doesn't exist"));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public List<Student> getStudents() {
        return STUDENT_LIST;
    }
}
