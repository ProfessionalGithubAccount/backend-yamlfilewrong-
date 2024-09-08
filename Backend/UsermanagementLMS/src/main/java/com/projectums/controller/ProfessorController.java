package com.projectums.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projectums.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectums.entity.Professor;
import com.projectums.service.ProfessorService;

@RestController
//@RequestMapping("/professor")
@RequestMapping("/user")
public class ProfessorController 
{	
	@Autowired
	private ProfessorService professorService;

	@GetMapping("/professorlist")
	public ResponseEntity<List<Professor>> getProfessorList() {
		List<Professor> professors = professorService.getAllProfessors();
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@GetMapping("/professorlistbyemail/{email}")
	public ResponseEntity<List<Professor>> getProfessorListByEmail(@PathVariable String email) {
		List<Professor> professors = professorService.getProfessorsByEmail(email);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@PostMapping("/addProfessor")
	public ResponseEntity<Professor> addNewProfessor(@RequestBody Professor professor) {
		Professor professorObj = professorService.addNewProfessor(professor);
		return new ResponseEntity<>(professorObj, HttpStatus.CREATED);
	}

	@GetMapping("/professorprofileDetails/{id}")
	public ResponseEntity<Professor> getProfileDetails(@PathVariable Integer id) {
		Optional<Professor> professor = professorService.fetchProfessorById(id);
		return professor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseThrow(() -> new ResourceNotFoundException("Professor not found with ID: " + id));
	}

	@PutMapping("/updateprofessor")
	public ResponseEntity<Professor> updateProfessorProfile(@RequestBody Professor professor) {
		Professor updatedProfessor = professorService.updateProfessorProfile(professor);
		return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
	}
	@GetMapping("/gettotalprofessors")
//	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getTotalProfessors() throws Exception
	{
		List<Professor> professors = professorService.getAllProfessors();
		List<Integer> professorsCount = new ArrayList<>();
		professorsCount.add(professors.size());
		return new ResponseEntity<List<Integer>>(professorsCount, HttpStatus.OK);
	}
	@GetMapping("/professor/{professorId}/courses")
	public List<Integer> getCourseIdsByProfessorId(@PathVariable Integer professorId) {
		return professorService.getCourseIdsByProfessorId(professorId);
	}


	
}
