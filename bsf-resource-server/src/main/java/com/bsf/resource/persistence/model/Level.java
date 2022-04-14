package com.bsf.resource.persistence.model;

import com.bsf.resource.persistence.model.audit.DateAudit;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Data
@Entity
@Table(name = "level")
@EntityListeners(AuditingEntityListener.class)
public class Level extends DateAudit {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "is_root", nullable = false, columnDefinition = "tinyint(1) default 0")
    private int isRoot;

    public Level() {}

    public Level( Employee employee, long parentId, long childId) {
        this.employee = employee;
        this.parentId = parentId;
        this.childId = childId;
    }

}
