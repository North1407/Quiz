package com.vti.examwebsise.examonline.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends IdBasedEntity implements Serializable {

    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false)
    private int trueAnswer;
    private boolean enabled = true;
    @Column(name = "raw_content", length = 1000)
    private String rawContent;
    @OneToOne
    @JoinColumn(name = "level_id")
    private Level level;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public Question(String content, Level level, int trueAnswer, Topic topic) {
        this.content = content;
        this.topic = topic;
        this.level = level;
        this.trueAnswer = trueAnswer;
    }

    public void addAnswer(String answerContent, boolean isCorrect) {
        this.answers.add(new Answer(answerContent, isCorrect, this));
    }

    public void addAnswer(Integer id, String answerContent, boolean isCorrect) {
        this.answers.add(new Answer(id, answerContent, isCorrect, this));
    }

    public List<Answer> getAllAnswers() {
        List<Answer> answerList = new ArrayList<>();
        for (Answer a : this.answers) {
            if (a.isCorrect()) {
                answerList.add(a);
            }
        }
        return answerList;
    }

    public List<Answer> getAllNonAnswers() {
        List<Answer> nonAnswerList = new ArrayList<>();
        for (Answer a : this.answers) {
            if (!a.isCorrect()) {
                nonAnswerList.add(a);
            }
        }
        return nonAnswerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(content, question.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @Override
    public String toString() {
        return "Question{" +
               "id=" + id +
               ", content='" + content + '\'' +
               ", topic='" + topic.getName() + '\'' +
               ", level='" + level + '\'' +
               '}';
    }
}
