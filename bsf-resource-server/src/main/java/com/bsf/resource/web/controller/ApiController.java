package com.bsf.resource.web.controller;

import com.bsf.resource.config.AppConfig;
import com.bsf.resource.exception.InvalidReferenceIdException;
import com.bsf.resource.persistence.model.Employee;
import com.bsf.resource.persistence.model.Level;
import com.bsf.resource.service.EmployeeService;
import com.bsf.resource.service.LevelService;
import com.bsf.resource.service.contract.IAuthenticationFacade;
import com.bsf.resource.service.util.ModelMapper;
import com.bsf.resource.web.dto.request.*;
import com.bsf.resource.web.dto.response.*;
import com.bsf.resource.service.util.LoggerService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@SuppressWarnings({"unchecked", "rawtypes"})
@RestController
@RequestMapping(value = "/api/bsf/")
@Slf4j
public class ApiController {

    @Autowired
    private IAuthenticationFacade authFacade;

    @Autowired
    LoggerService loggerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    LevelService levelService;

    @Autowired
    AppConfig appConfig;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);


    /**
     * @param request
     * @return ResponseEntity
     */
    @PostMapping(path = "employee/create")
    public ResponseEntity<BaseResponse> addEmployee(@Valid @RequestBody EmployeeDTO.Request.Body request) {
        BaseResponse response;

        try {
            Employee employee = employeeService.save(request);
            EmployeeResponse employeeResponse = modelMapper.getEmployeeResponse(employee);
            response = new BaseResponse(Integer.toString(HttpStatus.OK.value()),
                    "Successful", employeeResponse);
        } catch (Exception exception) {
            logger.error("Error :: {}", exception.getMessage());
            response = new BaseResponse(Integer.toString(HttpStatus.UNPROCESSABLE_ENTITY.value()), exception.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    /**
     * @param request
     * @return ResponseEntity
     */
    @PostMapping(path = "employee/addLevel")
    public ResponseEntity<BaseResponse> addLevel(@Valid @RequestBody LevelDTO.Request.Body request) {
        BaseResponse response;

        Level level = levelService.save(request);
        LevelResponse levelResponse = modelMapper.getLevelResponse(level);
        response = new BaseResponse(Integer.toString(HttpStatus.OK.value()),
                "Successful", levelResponse);

        return ResponseEntity.ok().body(response);
    }

    /**
     * @param employeeId
     * @return ResponseEntity
     */
    @DeleteMapping(path = "employee/{employeeId}")
    public void removeEmployee(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
    }


    /**
     * @param
     * @return ResponseEntity
     */
    @GetMapping(path = "/me")
    public ResponseEntity<BaseResponse>  loggedInUserDetails() {
        String username = authFacade.getAuthentication().getName();
        UserDetails userDetails = employeeService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new InvalidReferenceIdException("Unable to process request");
        }

        BaseResponse response = new BaseResponse(Integer.toString(HttpStatus.OK.value()),
                "Successful", userDetails);
        return ResponseEntity.ok().body(response);
    }

    /**
     * @return ResponseEntity
     */
    @GetMapping(path = "employees")
    public ResponseEntity<BaseResponse> employeeAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {

        BaseResponse response;
        Pageable paging = PageRequest.of(page, size);
        PaginatedEmployeesResponse paginatedEmployeesResponse = employeeService.findPaginatedResult(paging);

        response = new BaseResponse(Integer.toString(HttpStatus.OK.value()), "Successful", paginatedEmployeesResponse);

        return ResponseEntity.ok().body(response);
    }
}
