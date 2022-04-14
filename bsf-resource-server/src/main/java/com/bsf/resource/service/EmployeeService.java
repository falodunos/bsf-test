package com.bsf.resource.service;

import com.bsf.resource.exception.MissingEntityException;
import com.bsf.resource.persistence.model.Employee;
import com.bsf.resource.persistence.repository.EmployeeRepository;
import com.bsf.resource.service.contract.IAuthenticationFacade;
import com.bsf.resource.web.dto.request.EmployeeDTO;
import com.bsf.resource.web.dto.response.PaginatedEmployeesResponse;
import com.google.gson.Gson;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService extends BaseService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PaginatedEmployeesResponse paginatedEmployeesResponse;

    public Employee save(EmployeeDTO.Request.Body request) {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);

        System.out.println("jsonRequest :: " + jsonRequest);

        Employee employee = new Gson().fromJson(jsonRequest, Employee.class);
        employee = employeeRepository.save(employee);

        return employee.getId() > 0 ? employee : null;
    }

    public Employee findById(long employeeId) {
        Optional<Employee> employeeOptional =  this.employeeRepository.findById(employeeId);
        return  employeeOptional.isPresent() ? employeeOptional.get() : null;
    }

    public void deleteById(Long employeeId) {
        if (!employeeRepository.findById(employeeId).isPresent())
            throw new MissingEntityException("Not found!, You cannot delete a non-exiting entity with with ID: " + employeeId);

        employeeRepository.deleteById(employeeId);
    }

    public PaginatedEmployeesResponse findPaginatedResult(Pageable pageable) {

        Page<Employee> pageEmployee = employeeRepository.findAll(pageable);
        List<Employee> employees = pageEmployee.getContent();

        paginatedEmployeesResponse.setEmployees(employees);
        paginatedEmployeesResponse.setCurrentPage(pageEmployee.getNumber());
        paginatedEmployeesResponse.setTotalPages(pageEmployee.getTotalPages());
        paginatedEmployeesResponse.setTotalItems(pageEmployee.getTotalElements());

        return paginatedEmployeesResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = User.withUsername(username).password("p4%%w0rd").authorities("USER").build();
        return user;
    }

}
