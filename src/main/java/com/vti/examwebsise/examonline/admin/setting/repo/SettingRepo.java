package com.vti.examwebsise.examonline.admin.setting.repo;

import com.vti.examwebsise.examonline.common.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SettingRepo extends JpaRepository<Setting, Integer> {
    @Query("select s.value from Setting s where s.name = ?1")
    Integer findByName(String name);
}
