package com.vti.examwebsise.examonline.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends IdBasedEntity {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String photos;
    @Column(nullable = false)
    private boolean enabled;
    @Column(name = "in_exam",nullable = false)
    private boolean inExam;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 10)
    private AuthenticationType authenticationType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    @Transient
    public String getPhotosImagePath() {
        if (id == null || photos == null) return "/images/default-user.png";
        return  "/user-photos/" + this.id + "/" + this.photos;
    }
}
