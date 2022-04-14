package com.bsf.resource.service.util;

import com.bsf.resource.persistence.model.Employee;
import com.bsf.resource.persistence.model.Level;
import com.bsf.resource.web.dto.response.EmployeeResponse;
import com.bsf.resource.web.dto.response.LevelResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class ModelMapper {

    @Autowired
    EmployeeResponse employeeResponse;

    @Autowired
    LevelResponse levelResponse;


    public EmployeeResponse getEmployeeResponse(Employee employee) {
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setTelephone(employee.getTelephone());
        return employeeResponse;
    }

    public LevelResponse getLevelResponse(Level level) {

        levelResponse.setEmployeeId(level.getId());
        levelResponse.setParentId(level.getParentId());
        levelResponse.setChildId(level.getChildId());
        levelResponse.setIsRoot(level.getIsRoot());

        return levelResponse;
    }
}
