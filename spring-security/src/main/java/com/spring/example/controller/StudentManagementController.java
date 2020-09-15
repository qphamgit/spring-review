package com.spring.example.controller;

import com.spring.example.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/student")
public class StudentManagementController {
    static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Tom"),
            new Student(2, "Pete")
    );

    // hasRole('ROLE_1') hasAnyRole('ROLE_1', 'ROLE_2') hasAuthority('permission') hasAnyAuthority('permission1', 'permission2')
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public static List<Student> getAllStudents() {
        return STUDENT_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));
    }
}
