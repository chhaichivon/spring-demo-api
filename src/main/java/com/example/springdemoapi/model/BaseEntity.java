package com.example.springdemoapi.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    public EStatus status;

    @ManyToOne
    @JoinColumn(name="created_by")
    public UserEntity createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    public Date createdAt;

    @ManyToOne
    @JoinColumn(name="updated_by")
    public UserEntity updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    public Date updatedAt;
}
