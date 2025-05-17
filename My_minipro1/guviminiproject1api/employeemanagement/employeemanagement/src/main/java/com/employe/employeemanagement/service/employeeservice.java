package com.employe.employeemanagement.service;

import com.employe.employeemanagement.model.employee;
import com.employe.employeemanagement.repository.employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class employeeservice {
    //injecting repository layer
    @Autowired
    private employeerepository employeerepository;

    //ADD NEW EMPLOYEE
    public Mono<employee> addEmployee(employee employee) {
        return employeerepository.save(employee);
    }

    //GET ALL EMPLOYEE
    public Flux<employee> getAllEmployee() {
        return employeerepository.findAll();
    }

    //GET BY ID
    public Mono<employee> getEmployeeById(String id) {
        return employeerepository.findById(id);
    }

    //UPDATE EMPLOYEE BY ID
    public Mono<employee> updateEmployee(String id , employee updatedEmployee) {
        return employeerepository.findById(id)
                .flatMap(exist -> {
                    exist.setName(updatedEmployee.getName());
                    exist.setEmail(updatedEmployee.getEmail());
                    exist.setDepartment(updatedEmployee.getDepartment());
                    exist.setSalary(updatedEmployee.getSalary());
                    return employeerepository.save(exist);

                });
    }

    //DELETE EMPLOYEE BY ID
    public Mono<Void> deleteEmployee(String id) {
        return employeerepository.deleteById(id);
    }

}
