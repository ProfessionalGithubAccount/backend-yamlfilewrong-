package com.capstone.assignmentmicroservice.repository;


import com.capstone.assignmentmicroservice.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Integer> {
    StudentAssignment findByStudentIdAndCourseId(Integer studentId, Integer courseId);
}
