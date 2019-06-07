package com.trial.onetomany.onetomany.model;

// used for the auditing purpose of the entities
// created at and updated at

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )

public abstract class AuditModel implements Serializable {

    @CreatedDate
    @Column(name = "created_at" , nullable = false, updatable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at" , nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date updateddAt;


    public Date getCreatedAt() {
        return createdAt;
    }

    public AuditModel setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdateddAt() {
        return updateddAt;
    }

    public AuditModel setUpdateddAt(Date updateddAt) {
        this.updateddAt = updateddAt;
        return this;
    }
}
