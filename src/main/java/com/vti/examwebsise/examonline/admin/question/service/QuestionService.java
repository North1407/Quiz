package com.vti.examwebsise.examonline.admin.question.service;

import com.vti.examwebsise.examonline.admin.level.repo.LevelRepository;
import com.vti.examwebsise.examonline.admin.topic.repo.TopicRepo;
import com.vti.examwebsise.examonline.common.entity.Level;
import com.vti.examwebsise.examonline.common.entity.Question;
import com.vti.examwebsise.examonline.admin.question.repo.QuestionRepo;
import com.vti.examwebsise.examonline.common.entity.Topic;
import com.vti.examwebsise.examonline.utils.HtmlUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionRepo repo;
    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private LevelRepository levelRepo;

    public List<Question> getAllQuestions(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return repo.findAllByRandom();
        }
        return repo.findAllByRandom(keyword);
    }

    public void save(Question question, String[] answerIds, String[] answerContents, String[] ansCorrects) {
        int trueCount = 0;
        for(int i = 0; i < answerIds.length; i++) {
            if(ansCorrects[i].equals("1")) {
                trueCount++;
            }
            if (answerIds[i].equals("0")) {
                question.addAnswer(answerContents[i], "1".equals(ansCorrects[i]));
            } else {
                question.addAnswer(Integer.valueOf(answerIds[i]), answerContents[i],"1".equals(ansCorrects[i]));
            }
        }
        question.setTrueAnswer(trueCount);
        question.setContent(HtmlUtils.addTag(question.getContent()));
        question.setRawContent(HtmlUtils.removeTag(question.getContent()));
        repo.save(question);
    }



    public void deleteQuestion(Integer id) {
        repo.deleteById(id);
    }

    public Question getQuestionById(Integer id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("Question not found"));
    }

    public void enableQuestion(Integer id, boolean status) {
        Question question = repo.findById(id).orElseThrow(()->new RuntimeException("Question not found"));
        question.setEnabled(status);
        repo.save(question);
    }

    public List<Question> exportQuestions(List<Question> currentQuestions, String topicName, String levelName, String keyword) {
        List<Question> questions = new ArrayList<>();
        for (Question q : currentQuestions) {
            if (q.getTopic().getName().contains(topicName)
                    && q.getLevel().getLevel().contains(levelName)
                    && q.getContent().contains(keyword)) {
                questions.add(q);
            }
        }
       return questions;
    }

    public void importQuestions(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (currentRow.getRowNum() == 0) { // skip the header row
                    continue;
                }

                String questionContent = currentRow.getCell(0).getStringCellValue();
                String levelName = currentRow.getCell(1).getStringCellValue();
                if (levelName == null || levelName.isEmpty()) {
                    levelName = "easy";
                }

                String topicName = currentRow.getCell(2).getStringCellValue();
                String correctAnswer = currentRow.getCell(8).getStringCellValue();

                List<String> correctList = List.of(correctAnswer.split(""));

                // Fetch or create Level
                Level level = levelRepo.findByLevel(levelName);
                if (level == null) {
                    level = new Level(levelName);
                    levelRepo.save(level);
                }

                // Fetch or create Topic
                Topic topic = topicRepo.findByName(topicName);
                if (topic == null) {
                    topic = new Topic(topicName);
                    topicRepo.save(topic);
                }

                Question question = new Question();
                question.setContent(HtmlUtils.addTag(questionContent));
                question.setRawContent(HtmlUtils.removeTag(questionContent));
                question.setLevel(level);
                question.setTopic(topic);
                question.setTrueAnswer(correctList.size());

                for (int i = 3; i <= 7; i++) {
                    String answer = currentRow.getCell(i).getStringCellValue();
                    if (answer == null || answer.isEmpty()) {
                        continue;
                    }
                    question.addAnswer(answer, correctList.contains(String.valueOf(i - 2)));
                }

                repo.save(question);
            }

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to import questions from Excel file", e);
        }
    }
}
