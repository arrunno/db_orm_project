package dbProject.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_takes")
public class ExamTake {
    @Id
    @GeneratedValue
    private Long id;
//    @ManyToOne()
//    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REMOVE})
//    private ExamTakeInfo examTakeInfo;
    private String examId;
    private Integer questionNumber;
    private Integer userAnswer;
    private boolean isAnswerCorrect;

    public ExamTake(String examId, Integer questionNumber, Integer userAnswer) {
        this.examId = examId;
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "ExamTake{" +
                "id=" + id +
//                ", examTakeInfo='" + examTakeInfo + '\'' +
                ", examId='" + examId + '\'' +
                ", questionNumber=" + questionNumber +
                ", userAnswer=" + userAnswer +
                ", isAnswerCorrect=" + isAnswerCorrect +
                '}';
    }


}



