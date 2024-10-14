package com.storemanagement.jpa.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public abstract class AbstractDO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    protected void onCreate() {
        this.createdOn = now();
        this.updatedOn = now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = now();
    }
}
