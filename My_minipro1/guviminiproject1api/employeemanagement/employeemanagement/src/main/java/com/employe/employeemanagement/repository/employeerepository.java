package com.employe.employeemanagement.repository;

import com.employe.employeemanagement.model.employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeerepository extends ReactiveMongoRepository<employee,String> {


}
