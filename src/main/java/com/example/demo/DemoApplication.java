package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired EmployeeRepository empRepo;
	public static void main(String[] args){
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//empRepo.deleteAll();
		
		//save few employees
//		empRepo.save(new Employee(127, "nidhi", "kumari", "email@gmail.com"));
//		empRepo.save(new Employee(129, "nidhi1", "kumari", "email1@gmail.com"));
		
		//fetch all customers
		System.out.println("All customers");
		for(Employee emp : empRepo.findAll()) {
			System.out.println(emp);
		}
		System.out.println();
		
		//fetch individual employee
		System.out.println(empRepo.findByFirstName("nidhi"));
		System.out.println(empRepo.findByLastName("kumari"));
	}

}
