package com.capstone.coursemicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="usermanagement")
public interface StudentClient {
    @GetMapping("/user/{studentId}/courses")
    List<Integer> getCIDBySID(@PathVariable Integer studentId);
    @GetMapping("/user/professor/{professorId}/courses")
    List<Integer> getCIDByPID(@PathVariable Integer professorId);
}