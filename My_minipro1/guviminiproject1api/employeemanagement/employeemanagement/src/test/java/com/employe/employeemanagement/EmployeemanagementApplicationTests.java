package com.employe.employeemanagement;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.employe.employeemanagement.model.employee;
import com.employe.employeemanagement.repository.employeerepository;
import com.employe.employeemanagement.service.employeeservice;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeemanagementApplicationTests {

	@Mock
	private employeerepository employeerepository;

	@InjectMocks
	private employeeservice employeeservice;
//GET ALL EMPLOYEE
	@Test
	void testGetAllEmployees() {
		employee emp1 = new employee("1", "Priya", "priya@gmail.com", "HR", 50000);
		employee emp2 = new employee("2", "Kumar", "kumar@gmail.com", "IT", 60000);

		when(employeerepository.findAll()).thenReturn(Flux.just(emp1, emp2));

		Flux<employee> result = employeeservice.getAllEmployee();

		StepVerifier.create(result)
				.expectNext(emp1)
				.expectNext(emp2)
				.verifyComplete();

		verify(employeerepository,times(1)).findAll();
	}
// POST METHOD
@Test
void testCreateEmployee() {
	employee employee = new employee("1", "Priya", "priya@gmail.com", "HR", 50000);

	when(employeerepository.save(employee)).thenReturn(Mono.just(employee));

	Mono<employee> result = employeeservice.addEmployee(employee);

	StepVerifier.create(result)
			.expectNext(employee)
			.verifyComplete();

	verify(employeerepository, times(1)).save(employee);
}
//get by {id}
@Test
void testGetEmployeeById() {
	String employeeId = "1";
	employee employee = new employee(employeeId, "Priya", "priya@gmail.com", "HR", 50000);

	when(employeerepository.findById(employeeId)).thenReturn(Mono.just(employee));

	Mono<employee> result = employeeservice.getEmployeeById(employeeId);

	StepVerifier.create(result)
			.expectNext(employee)
			.verifyComplete();

	verify(employeerepository, times(1)).findById(employeeId);
}
//delete by id
@Test
void testDeleteEmployee() {
	String employeeId = "1";

	when(employeerepository.deleteById(employeeId)).thenReturn(Mono.empty());

	Mono<Void> result = employeeservice.deleteEmployee(employeeId);

	StepVerifier.create(result)
			.verifyComplete();

	verify(employeerepository, times(1)).deleteById(employeeId);
}



}
