package com.capstone.coursemicroservice.controller;


import com.capstone.coursemicroservice.entity.Course;
import com.capstone.coursemicroservice.exception.ResourceNotFoundException;
import com.capstone.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses available.");
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getAllCoursesByStudentId(@PathVariable Integer studentId) {
        List<Course> courses = courseService.getAllCoursesByStudentId(studentId);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable Integer professorId) {
        List<Course> courses = courseService.getAllCoursesByProfessorId(professorId);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for professor with ID: " + professorId);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/showResource/{courseId}")
    public ResponseEntity<ByteArrayResource> showResource(@PathVariable Integer courseId) {
        byte[] resourceData = courseService.getResourceByCourseId(courseId);
        if (resourceData == null) {
            throw new ResourceNotFoundException("Resource not found for course ID: " + courseId);
        }

        ByteArrayResource resource = new ByteArrayResource(resourceData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"course_" + courseId + ".pdf\"")
                .contentLength(resourceData.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}


//
//import com.capstone.coursemicroservice.entity.Course;
//import com.capstone.coursemicroservice.service.CourseService;
//import com.projectums.entity.Student;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/courses")
//public class CourseController {
//    @Autowired
//    private CourseService courseService;
//
//    @GetMapping("/getAll")
//    public List<Course> getAllCourses() {
//
//        return courseService.getAllCourses();
//    }
//
//
//    @PostMapping("/addCourse")
//    public Course addCourse(@RequestBody Course course) {
//
//        return courseService.saveCourse(course);
//    }
//
//    @PutMapping("/updateCourse")
//    public Course updateCourse(@RequestBody Course course) {
//
//        return courseService.updateCourse(course);
//    }
//
//    @GetMapping("/student/{studentId}")
//    public List getAllCoursesByStudentId(@PathVariable Integer studentId) {
//        return courseService.getAllCoursesByStudentId(studentId);
//    }
//
//
//
//    @GetMapping("/professor/{professorId}")
//    public List<Course> getAllCoursesByProfessorId(@PathVariable Integer professorId) {
//
//        return courseService.getAllCoursesByProfessorId(professorId);
//    }
//
//    @GetMapping("/showResource/{courseId}")
//    public ResponseEntity<ByteArrayResource> showResource(@PathVariable Integer courseId) {
//        byte[] resourceData = courseService.getResourceByCourseId(courseId);
//        if (resourceData == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(resourceData);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"course_" + courseId + ".pdf\"")
//                .contentLength(resourceData.length)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(resource);
//    }
//
//    }
//
//
////    public List<Course> getAssignmentsByCourseId(@PathVariable Integer courseId) {
////        return courseService.getAssignmentsByCourseId();
////    }
////    @GetMapping("/getOverallScore")
////    public int getOverallScore(@RequestParam Integer courseId, @RequestParam List<Long> assignmentIds) {
////        return courseService.getOverallScore(courseId);
////    }
//
//
