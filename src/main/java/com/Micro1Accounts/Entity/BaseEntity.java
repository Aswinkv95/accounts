package com.Micro1Accounts.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(insertable = false, updatable = true)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false, updatable = true)
    private String updatedBy;
}
