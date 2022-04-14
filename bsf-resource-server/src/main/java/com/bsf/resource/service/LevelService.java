package com.bsf.resource.service;

import com.bsf.resource.exception.MissingEntityException;
import com.bsf.resource.persistence.model.Employee;
import com.bsf.resource.persistence.model.Level;
import com.bsf.resource.persistence.repository.LevelRepository;
import com.bsf.resource.service.factory.LevelFactory;
import com.bsf.resource.web.dto.request.LevelDTO;
import com.google.gson.Gson;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class LevelService extends BaseService {

    @Autowired
    LevelFactory levelFactory;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    EmployeeService employeeService;

    private static final Logger log = LoggerFactory.getLogger(LevelService.class);

    public Level save(LevelDTO.Request.Body request) throws EntityNotFoundException {
        log.info("Level Create / Update Request :: " + new Gson().toJson(request));

        long employeeId = request.getEmployeeId();
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) throw new MissingEntityException("Employee with ID '" + employeeId + "' Not Found!");

        Level level = levelFactory.createLevel(request, employee);

        Optional<Level> levelOptional = levelRepository.findByEmployeeId(employeeId);
        if (levelOptional.isPresent()) {
            level = levelOptional.get();
            level.setEmployee(employee);
            level.setParentId(request.getParentId());
            level.setChildId(request.getChildId());
        }

        if (levelRepository.findAll().size() == 0) level.setIsRoot(1);

        level = levelRepository.save(level);

        return level.getId() > 0 ? level : null;
    }
}
