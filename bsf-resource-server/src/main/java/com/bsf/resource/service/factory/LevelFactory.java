package com.bsf.resource.service.factory;

import com.bsf.resource.persistence.model.Employee;
import com.bsf.resource.persistence.model.Level;
import com.bsf.resource.web.dto.request.LevelDTO;
import org.springframework.stereotype.Service;

@Service
public class LevelFactory {

    public Level createLevel() {
        return new Level();
    }

    public Level createLevel(LevelDTO.Request.Body request, Employee employee) {
        return new Level(employee, request.getParentId(), request.getChildId());
    }
}
