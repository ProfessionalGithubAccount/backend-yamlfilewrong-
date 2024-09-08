package com.capstone.coursemicroservice.service;


import java.util.List;
import java.util.Optional;

import com.capstone.coursemicroservice.client.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capstone.coursemicroservice.entity.Course;
import com.capstone.coursemicroservice.exception.ResourceNotFoundException;
import com.capstone.coursemicroservice.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCoursesByStudentId(Integer studentId) {
//        String studentServiceUrl = "/user/" + studentId + "/courses";
//        HttpEntity<String> entity = new HttpEntity<>(null, null);
//        ResponseEntity<List> response = restTemplate.exchange(studentServiceUrl, HttpMethod.GET, entity, List.class);
//
//        if (response.getBody() == null || response.getBody().isEmpty()) {
//            throw new ResourceNotFoundException("No courses found for student ID: " + studentId);
//        }
//
//        return courseRepository.findAllById(response.getBody());
        List<Integer> courseIds = studentClient.getCIDBySID(studentId);

        return courseRepository.findAllById(courseIds);
    }

    public List<Course> getAllCoursesByProfessorId(Integer professorId) {
//        String teacherServiceUrl = "http://localhost:5555/user/professor/" + professorId + "/courses";
//        List<Integer> courseIds = restTemplate.getForObject(teacherServiceUrl, List.class);
//
//        if (courseIds == null || courseIds.isEmpty()) {
//            throw new ResourceNotFoundException("No courses found for professor ID: " + professorId);
//        }
//
//        return courseRepository.findAllById(courseIds);
        List<Integer> courseIds = studentClient.getCIDByPID(professorId);

        return courseRepository.findAllById(courseIds);
    }

    public byte[] getResourceByCourseId(Integer courseId) {
        Course course = getCourseById(courseId);  // Reuse existing method to check if course exists
        return course.getResource();
    }
}






//
//import java.util.List;
//
//import com.capstone.coursemicroservice.client.StudentClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.capstone.coursemicroservice.entity.Course;
//import com.capstone.coursemicroservice.repository.CourseRepository;
//import com.projectums.entity.Student;
//
//@Service
//public class CourseService {
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private StudentClient studentClient;
//
//
//
//    public List<Course> getAllCourses() {
//        return courseRepository.findAll();
//    }
//
//    public Course getCourseById(Integer courseId) {
//        return courseRepository.findById(courseId).orElse(null);
//    }
//
//    public Course saveCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public Course updateCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public byte[] getResourceByCourseId(Integer courseId) {
//        Course course = courseRepository.findById(courseId).orElse(null);
//        return course != null ? course.getResource() : null;
//    }
//
//
//    public List getAllCoursesByStudentId(Integer studentId) {
//        List<Integer> courseIds = studentClient.getCIDBySID(studentId);
//        return courseRepository.findAllById(courseIds);
//    }
//
//    public List<Course> getAllCoursesByProfessorId(Integer professorId) {
//        List<Integer> courseIds = studentClient.getCIDByPID(professorId);
//        return courseRepository.findAllById(courseIds);
//    }
//
//
////    public List<Student> getStudentsByCourseId(Integer courseId) {
////        String url = "http://5555/user/byCourse/" + courseId;
////        List<Student> students = restTemplate.getForObject(url, List.class);
////        return students;
////    }
//}
//
//
//
////public List<Course> getAllCoursesByStudentId(Integer studentId) {
////    String studentServiceUrl = "/user/" + studentId + "/courses";
////
////    // Create an empty HttpEntity since no headers or body are needed
////    HttpEntity<String> entity = new HttpEntity<>(null, null);
////
////    // Correct the exchange method usage
////    ResponseEntity<List> response = restTemplate.exchange(
////            studentServiceUrl,
////            HttpMethod.GET,
////            entity,
////            List.class
////    );
////
////    return courseRepository.findAllById(response.getBody());
////}
////
////public List<Course> getAllCoursesByProfessorId(Integer professorId) {
////    String teacherServiceUrl = "http://localhost:5555/user/professor/" + professorId + "/courses"; // Adjust the URL as necessary
////    List<Integer> courseIds = restTemplate.getForObject(teacherServiceUrl, List.class);
////    return courseRepository.findAllById(courseIds);
////}
