package com.api.api.payload;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

     @Size(min = 3, max = 15, message = "Name should be between 3 to 15 letters")
     private String name;

     @Email(message = "Enter valid email Id")
     private String email;

     @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
     private String mobile;
}
