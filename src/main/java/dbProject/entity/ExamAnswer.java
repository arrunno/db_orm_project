package dbProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_answers")
public class ExamAnswer {
    @Id
    @GeneratedValue
    private Long id;
    @JoinColumn(name = "examTakeInfoId")
    @ManyToOne(cascade = CascadeType.ALL)
    private ExamTakeInfo examTakeInfo;
    private String examId;
    private Integer questionNumber;
    private Integer userAnswer;
    private boolean isAnswerCorrect;

    public ExamAnswer(String examId, Integer questionNumber, Integer userAnswer) {
        this.examId = examId;
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return  "examId='" + examId + '\'' +
                ", questionNumber=" + questionNumber +
                ", userAnswer=" + userAnswer +
                ", isAnswerCorrect=" + isAnswerCorrect +
                ", examTakeInfoId=" + getExamTakeInfo().getId();
    }


}



