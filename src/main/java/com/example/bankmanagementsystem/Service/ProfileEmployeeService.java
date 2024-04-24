package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.ProfileEmployee;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import com.example.bankmanagementsystem.Repository.ProfileEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileEmployeeService {

    private final ProfileEmployeeRepository profileEmployeeRepository;
    private final EmployeeRepository employeeRepository;

    //ADMIN
    public List<ProfileEmployee> getProfileEmployee() {
        return profileEmployeeRepository.findAll();
    }
    //EMPLOYEE
    public void addProfile(Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);

        ProfileEmployee profile =new ProfileEmployee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),employee);
        profileEmployeeRepository.save(profile);
    }
    //EMPLOYEE
    public void updateProfile(Integer employeeId, EmployeeDTO employeeDTO) {
       ProfileEmployee profile = profileEmployeeRepository.findByEmployeeId(employeeId);

        profile.setSalary(employeeDTO.getSalary());
        profile.setPosition(employeeDTO.getPosition());
        profileEmployeeRepository.save(profile);

    }
    //EMPLOYEE
    public void deleteProfile(Integer employeeId) {
        ProfileEmployee profile = profileEmployeeRepository.findByEmployeeId(employeeId);
        if (profile == null) {
            throw new ApiException("profile not found");
        }
       profileEmployeeRepository.delete(profile);
    }


}
