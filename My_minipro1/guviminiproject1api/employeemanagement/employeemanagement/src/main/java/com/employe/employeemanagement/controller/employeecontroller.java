package com.employe.employeemanagement.controller;


import com.employe.employeemanagement.exception.ResourceNotFoundException;
import com.employe.employeemanagement.model.employee;
import com.employe.employeemanagement.service.employeeservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*") //for frontend connection
public class employeecontroller {

    //injecting Service layer
    @Autowired
    private employeeservice employeeservice;

    //CREATE NEW EMPLOYEE
    @PostMapping
    public Mono<employee> addEmployee(@Valid @RequestBody employee employee) {
        return employeeservice.addEmployee(employee);
    }

    //GET ALL EMPLOYEE
    @GetMapping
    public Flux<employee> getAllEmployee() {
        return employeeservice.getAllEmployee();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Mono<employee> getEmployeeById(@PathVariable String id) {
        return employeeservice.getEmployeeById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Employee with id " + id + " not found")));
    }



    //UPDATE BY ID
    @PutMapping("/{id}")
    public Mono<employee> updateEmployee(@PathVariable String id, @Valid @RequestBody employee employee) {
        return employeeservice.updateEmployee(id, employee)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cannot update. Employee with id " + id + " not found")));
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    public Mono<Void> deleteEmployee(@PathVariable String id) {
        return employeeservice.getEmployeeById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cannot delete. Employee with id " + id + " not found")))
                .flatMap(emp -> employeeservice.deleteEmployee(id));
    }

}
