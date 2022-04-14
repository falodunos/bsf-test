package com.bsf.resource.web.dto.response;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class LevelResponse {
    private long employeeId;
    private long parentId;
    private long childId;
    private int isRoot;

    public LevelResponse() {}
}
