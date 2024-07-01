package com.vti.examwebsise.examonline;

import com.vti.examwebsise.examonline.common.entity.Question;
import com.vti.examwebsise.examonline.admin.question.repo.QuestionRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(value = false)
public class TestCreateQandA {
    @Autowired
    QuestionRepo questionRepo;

    @Test
    public void view(){
        Question question = questionRepo.getById(11);
        String header = question.getRawContent().split("\\n")[0];
        System.out.println(header);
        String code = question.getRawContent().split(header)[1];
        System.out.println(code);
        }

}
