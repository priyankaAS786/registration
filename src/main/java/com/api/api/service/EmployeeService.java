package com.api.api.service;

import com.api.api.entity.Employee;
import com.api.api.payload.EmployeeDto;
import com.api.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee mapedEmp = mapToEmployee(employeeDto);
        Employee savedEmp = employeeRepository.save(mapedEmp);
        EmployeeDto savedDto = mapToEmployeeDto(savedEmp);
        return savedDto;
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> allEmp = employeeRepository.findAll();
        List<EmployeeDto> dtos = allEmp.stream().map(r -> mapToEmployeeDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee e = employeeRepository.findById(id).get();
        e.setName(employee.getName());
        e.setEmail(employee.getEmail());
        e.setMobile(employee.getMobile());
        Employee savedEmp = employeeRepository.save(e);
        return savedEmp;
    }

    public Employee mapToEmployee(EmployeeDto employeeDto){
        Employee emp = new Employee();
        emp.setName(employeeDto.getName());
        emp.setEmail(employeeDto.getEmail());
        emp.setMobile(employeeDto.getMobile());
        return emp;
    }

    public EmployeeDto mapToEmployeeDto(Employee savedEmp){
        EmployeeDto DtoEmp = new EmployeeDto();
        DtoEmp.setName(savedEmp.getName());
        DtoEmp.setEmail(savedEmp.getEmail());
        DtoEmp.setMobile(savedEmp.getMobile());
        return DtoEmp;
    }
    
    

}
