package com.capstone.assignmentmicroservice.service;

import com.capstone.assignmentmicroservice.entity.Assignment;
import com.capstone.assignmentmicroservice.entity.StudentAssignment;
import com.capstone.assignmentmicroservice.repository.AssignmentRepository;
import com.capstone.assignmentmicroservice.repository.StudentAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentAssignmentRepository studentCourseMarksRepository;


    public List<Map<String, Object>> getAllAssignments() {
        String sql = "SELECT assignment_id, professor_id,course_id, title,deadline,file_name FROM assignment ";
        return jdbcTemplate.queryForList(sql);
    }



    public byte[] showAssignmentFileById(Integer id) {
        return assignmentRepository.findById(id)
                .map(assignment -> assignment.getAssignmentFile())
                .orElse(null);
    }

    public List<Map<String, Object>> getAssignmentsByCourseId(Integer courseId) {
        String sql = "SELECT assignment_id, professor_id, title,deadline,file_name FROM assignment WHERE course_id = ?";
        return jdbcTemplate.queryForList(sql, courseId);
    }

    public List<Integer> getStudentAssignmentMarks(Integer studentId, Integer courseId) {
        StudentAssignment studentAssignment = studentCourseMarksRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (studentAssignment == null || studentAssignment.getMarks().isEmpty()) {
            throw new IllegalArgumentException("No marks found for student " + studentId + " in course " + courseId);
        }
        return studentAssignment.getMarks();
    }

    public StudentAssignment saveStudentMarks(Integer studentId, Integer courseId, List<Integer> marks) {
        StudentAssignment studentAssignment = studentCourseMarksRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (studentAssignment == null) {
            studentAssignment = new StudentAssignment();
            studentAssignment.setStudentId(studentId);
            studentAssignment.setCourseId(courseId);
        }
        studentAssignment.setMarks(marks);
        return studentCourseMarksRepository.save(studentAssignment);
    }


    public ResponseEntity<String> saveAssignment(MultipartFile file, String title, Integer id, Integer courseid, String deadline) throws IOException, ParseException {
		Date deadlineDate = new SimpleDateFormat("yyyy-MM-dd").parse(deadline);
		Assignment assignment = new Assignment();
		assignment.setTitle(title);
		assignment.setAssignmentId(id);
		assignment.setCourseId(courseid);
        assignment.setFileName(file.getOriginalFilename());
        assignment.setAssignmentFile(file.getBytes());
        assignment.setDeadline(deadlineDate);
		
		assignmentRepository.save(assignment);
		return ResponseEntity.ok("Assignment uploaded successfully.");
    }
//
//    public void changeColumnType() {
//        String sql = "ALTER TABLE assignment MODIFY COLUMN assignment_file MEDIUMBLOB";
////        String alterIdSql = "ALTER TABLE assignment MODIFY COLUMN assignmentId INT AUTO_INCREMENT PRIMARY KEY";
//        jdbcTemplate.execute(sql);
////        jdbcTemplate.execute(alterIdSql);
//    }
//    public List<Map<String, Object>> getDeadlines(Integer courseId) {
//        String sql = "SELECT title, deadline FROM assignment WHERE course_id = ?";
//        List<Map<String, Object>> assignments = jdbcTemplate.queryForList(sql, courseId);
//        String courseServiceUrl = "http://localhost:8761/eureka/courses/" + courseId; // Assuming course service is running on localhost:5002
//        Map courseResponse = restTemplate.getForObject(courseServiceUrl, Map.class);
//        String courseName = courseResponse != null ? (String) courseResponse.get("courseName") : null;
//         return assignments.stream().map(assignment -> {
//            Map<String, Object> result = new HashMap<>(assignment);
//            result.put("courseName", courseName);
//            return result;
//        }).collect(Collectors.toList());
//    }
}