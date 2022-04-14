package com.bsf.resource.web.dto.response;

import com.bsf.resource.persistence.model.Employee;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PaginatedEmployeesResponse {
    private List<Employee> employees;
    private int currentPage;
    private long totalItems;
    private int totalPages;


}
