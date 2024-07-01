package com.vti.examwebsise.examonline.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="levels")
public class Level extends IdBasedEntity {
    @Column(nullable = false, unique = true,length = 30)
    private String level;
    private boolean enabled = true;

    public Level(Integer id) {
        this.id = id;
    }
    public Level(String levelName) {
        this.level = levelName;
    }
}
