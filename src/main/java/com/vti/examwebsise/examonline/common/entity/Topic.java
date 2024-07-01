package com.vti.examwebsise.examonline.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Topic extends IdBasedEntity {
    @Column(nullable = false,unique = true)
    private String name;
    private boolean enabled = true;

    public Topic(Integer id) {
        this.id = id;
    }

    public Topic(String name) {
        this.name = name;
    }

}
