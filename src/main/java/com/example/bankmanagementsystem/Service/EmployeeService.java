package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
private final AuthRepository authRepository;


    //ADMIN
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    //EMPLOYEE
    public void register(User user) {

        user.setRole("EMPLOYEE");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
        Employee employee = new Employee();
        employee.setUser(user);
        employeeRepository.save(employee);


    }

    //EMPLOYEE
    public void updateEmployee( Integer employeeId,Employee employee) {

        Employee emp = employeeRepository.findEmployeeById(employeeId);
        if (emp == null) {
            throw new ApiException("not found");
        }
        emp.setProfileEmployee(employee.getProfileEmployee());
        emp.setUser(employee.getUser());
       employeeRepository.save(employee);

    }

    //ADMIN
    public void deleteEmployee(Integer employeeId) {
        Employee emp = employeeRepository.findEmployeeById(employeeId);
        if (emp == null) {
            throw new ApiException("not found");
        }
       employeeRepository.delete(emp);
    }
}
