package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmployeeRepository extends MongoRepository<Employee, Integer>{

	@Query("{'firstName' : ?0}")
	public Employee findByFirstName(String firstName);
	@Query(value="{'lastName' : ?0}")
	public List<Employee> findByLastName(String lastName);
	
}