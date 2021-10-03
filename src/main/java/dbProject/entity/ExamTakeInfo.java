package dbProject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_takes_info", uniqueConstraints = @UniqueConstraint(columnNames = {"examId", "userEmail", "examTakeDate"}))
public class ExamTakeInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String examId;
    private String userEmail;
    private String examInfo;
    private LocalDate examTakeDate;
    private int numberOfCorrectAnswers;
    private int totalExamQuestions;

    public ExamTakeInfo(String examId, String userEmail, String examInfo, LocalDate examTakeDate) {
        this.examId = examId;
        this.userEmail = userEmail;
        this.examInfo = examInfo;
        this.examTakeDate = examTakeDate;
    }
}
