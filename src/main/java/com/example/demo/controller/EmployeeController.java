package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Employee;
import com.example.demo.EmployeeRepository;

@RestController
public class EmployeeController{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("/show")
	public List<Employee> getEmployees(){

		return empRepo.findAll();
	}
	
	@GetMapping("/show/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Integer empID) {
		try {
			return empRepo.findById(empID)
					.orElseThrow(()-> new Exception("Employee not found on :: " + empID));
		}catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/create")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return empRepo.save(emp);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer empID, @Valid @RequestBody Employee emp){
		try {
			Employee e = empRepo.findById(empID)
					.orElseThrow(()-> new Exception("Employee not found on :: " + empID));
			e.setEmail(emp.getEmail());
			e.setFirstName(emp.getFirstName());
			e.setLastName(emp.getLastName());
			Employee updatedEmp = empRepo.save(e);
			return ResponseEntity.ok(updatedEmp);
		}catch (Exception e) {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer empID) throws Exception{
		Employee e = empRepo.findById(empID)
				.orElseThrow(()-> new Exception("Employee not found on :: " + empID));
		empRepo.delete(e);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("deleted", true);
		return map;
	}
}