package com.vti.examwebsise.examonline.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class IdBasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
}