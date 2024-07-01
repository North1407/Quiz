package com.vti.examwebsise.examonline.admin.question.repo;

import com.vti.examwebsise.examonline.common.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
    @Query("SELECT q FROM Question q where q.topic.name like %:topicName% and q.level.level like %:difficulty% order by rand()")
    List<Question> getExamQuestion(String topicName, String difficulty);
    @Query("SELECT q FROM Question q where q.rawContent like %?1% order by rand()")
    List<Question> findAllByRandom(String keyword);
    @Query("SELECT q FROM Question q order by rand()")
    List<Question> findAllByRandom();
}
