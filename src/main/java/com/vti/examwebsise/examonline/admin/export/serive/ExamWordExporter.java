package com.vti.examwebsise.examonline.admin.export.serive;

import com.vti.examwebsise.examonline.common.entity.Answer;
import com.vti.examwebsise.examonline.common.entity.Question;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExamWordExporter {
    public void exportDocx(List<Question> questions, String topicName, String uuid, HttpServletResponse response) {
        try {
            XWPFDocument document = new XWPFDocument();
            setUuidAndNameParagraph(document,uuid);
            setTitleParagraph(document, topicName);
            int questionNumber = 1;
            for (Question question : questions) {
                setQuestionParagraph(question, document, questionNumber);
                setAnswersParagraph(question.getAnswers(), document);
                questionNumber++;
            }
            export(response, document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUuidAndNameParagraph(XWPFDocument document, String uuid) {
        XWPFParagraph uuidAndNameParagraph = document.createParagraph();
        uuidAndNameParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun uuidAndNameRun = uuidAndNameParagraph.createRun();
        uuidAndNameRun.setFontSize(11);
        uuidAndNameRun.setText("Mã đề: " + uuid);
        uuidAndNameRun.addCarriageReturn();
        uuidAndNameRun.setText("Họ và tên: ");
        uuidAndNameRun.addCarriageReturn();
    }

    private void setTitleParagraph(XWPFDocument document, String topicName) {
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(15);
        titleRun.setText("VTI Exam Test" + ("".equals(topicName)? "": " - " + topicName));
    }
    private void setQuestionParagraph(Question question, XWPFDocument document, int questionNumber) {
        XWPFParagraph questionParagraph = document.createParagraph();
        String header = question.getRawContent().split("\r\n")[0];
        String code = question.getRawContent().substring(header.length()+2);

        XWPFRun questionRun = questionParagraph.createRun();
        questionRun.setText("Câu hỏi " + questionNumber + ": " + header);
        questionRun.setFontFamily("Arial"); // Đặt font chữ
        questionRun.setFontSize(12);
        questionRun.setBold(true);
        questionRun.addCarriageReturn();
        if (!code.isEmpty()) {
            XWPFRun codeRun = questionParagraph.createRun();
            codeRun.addCarriageReturn();
            List<String> codeLines = List.of(code.substring(0, code.length() - 3).split("\r\n"));
            for (String codeLine : codeLines) {
                codeRun.setText(codeLine);
                codeRun.addCarriageReturn();
            }
            codeRun.setFontFamily("Arial"); // Đặt font chữ
            codeRun.setFontSize(11);
            codeRun.setItalic(true);
        }
        if (question.getTrueAnswer() > 1) {
            XWPFRun questionRun2 = questionParagraph.createRun();
            questionRun2.setFontSize(11);
            questionRun2.setItalic(true);
            questionRun2.setText("(Có nhiều đáp án đúng)");
        }
    }
    private void setAnswersParagraph(List<Answer> answers, XWPFDocument document) {
        char answerNumber = 'A';
        XWPFParagraph answersParagraph = document.createParagraph();
        XWPFRun answersRun = answersParagraph.createRun();
        answersRun.setFontSize(12);

        for (Answer answer : answers) {
            answersRun.setText(answerNumber + ")  " + answer.getContent());
            answerNumber++;
            answersRun.addCarriageReturn();
        }
    }
    private void export(HttpServletResponse response, XWPFDocument document) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timestamp = dateFormatter.format(new Date());
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition", "attachment; filename=exam" + timestamp + ".docx");
        response.setContentLength(byteArray.length);
        response.getOutputStream().write(byteArray);
    }
}
