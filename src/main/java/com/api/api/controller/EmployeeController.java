package com.api.api.controller;

import com.api.api.entity.Employee;
import com.api.api.payload.EmployeeDto;
import com.api.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController{


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addemployee")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result){
        EmployeeDto savedDto = employeeService.addEmployee(employeeDto);

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED); //status code = 201
    }

    @GetMapping("/getemployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allDtos = employeeService.getEmployees();
        return new ResponseEntity<>(allDtos,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?id=3
    @DeleteMapping()
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmp = employeeService.updateEmployeeById(id,employee);
        return new ResponseEntity<>(updatedEmp,HttpStatus.OK); //200
    }
}