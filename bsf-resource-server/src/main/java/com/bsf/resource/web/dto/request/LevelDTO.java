package com.bsf.resource.web.dto.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

public enum LevelDTO {;
    private interface Id {
        /**
         * @return Long
         */
        long getId();
    }

    private interface EmployeeId {
        /**
         * @return Long
         */
        @NotNull(message = "Please provide a valid employee Id")
        long getEmployeeId();
    }

    private interface ParentId {
        /**
         * @return Long
         */
        long getParentId();
    }

    private interface ChildId {
        /**
         * @return Long
         */
        long getChildId();
    }

    public enum Request {
        ;

        @Value
        public static class Body implements Id, EmployeeId, ParentId, ChildId {
            long id;
            long employeeId;
            long parentId;
            long childId;
        }
    }
}
