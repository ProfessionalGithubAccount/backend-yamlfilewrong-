package com.projectums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projectums.entity.Professor;
import com.projectums.entity.Student;
import com.projectums.repository.ProfessorRepository;
import com.projectums.repository.StudentRepository;
import com.projectums.service.ProfessorService;
import com.projectums.service.StudentService;
import com.projectums.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ProfessorRepository professorRepo;

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to Elearning Management system !!!";
    }

    @PostMapping("/loginstudent")
    public ResponseEntity<Integer> loginStudent(@RequestBody Student student) {
        Student s = studentRepo.findByUsername(student.getUsername());

        if (s == null) {
            throw new ResourceNotFoundException("Student not found with username: " + student.getUsername());
        }

        if (studentService.authenticate(student.getUsername(), student.getPassword())) {
            return ResponseEntity.ok(s.getStudentId());
        } else {
            return ResponseEntity.status(401).body(0);
        }
    }

    @PostMapping("/loginprofessor")
    public ResponseEntity<Integer> loginProfessor(@RequestBody Professor professor) {
        Professor prof = professorRepo.findByUsername(professor.getUsername());

        if (prof == null) {
            throw new ResourceNotFoundException("Professor not found with username: " + professor.getUsername());
        }

        if (professorService.authenticate(professor.getUsername(), professor.getPassword())) {
            return ResponseEntity.ok(prof.getProfessorId());
        } else {
            return ResponseEntity.status(401).body(0);
        }
    }
}


//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.projectums.entity.Professor;
//import com.projectums.entity.Student;
//import com.projectums.repository.ProfessorRepository;
//import com.projectums.repository.StudentRepository;
//import com.projectums.service.ProfessorService;
//import com.projectums.service.StudentService;
//
//@RestController
//@RequestMapping("/user")
//public class LoginController {
//
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private ProfessorService professorService;
//
//    @Autowired
//    private StudentRepository studentRepo;
//
//    @Autowired
//    private ProfessorRepository professorRepo;
//
//    @GetMapping("/")
//    public String welcomeMessage() {
//        return "Welcome to Elearning Management system !!!";
//    }
//
//    @PostMapping("/loginstudent")
////    @CrossOrigin(origins = "http://localhost:4200")
//    public ResponseEntity<Integer> loginStudent(@RequestBody Student student) {
//        Integer response ;
//        System.out.println(student);
//        Student s=new Student();
//        s=studentRepo.findByUsername(student.getUsername());
//        // Here we validate credentials without using JWT
//        if (studentService.authenticate(student.getUsername(), student.getPassword())) {
////            response.put("Login successful", 1);
//            response=(s.getStudentId()); // You can customize the response as needed
//            return ResponseEntity.ok(response);
//        } else {
//            response=0;
//            return ResponseEntity.status(401).body(response);
//        }
//    }
//
//    @PostMapping("/loginprofessor")
////    @CrossOrigin(origins = "http://localhost:4200")
//    public ResponseEntity<Integer> loginProfessor(@RequestBody Professor professor) {
//        Integer response;
//        System.out.println(professor);
//        Professor prof=new Professor();
//        prof=professorRepo.findByUsername(professor.getUsername());
//
//        // Here we validate credentials without using JWT
//        if (professorService.authenticate(professor.getUsername(), professor.getPassword())) {
//        	response=(prof.getProfessorId()); // You can customize the response as needed
//            return ResponseEntity.ok(response);
//        } else {
//        	response=0;
//            return ResponseEntity.status(401).body(response);
//        }
//    }
//}
