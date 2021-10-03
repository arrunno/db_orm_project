package dbProject.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.CascadeType;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_takes")
public class ExamTake {
    @Id
    @GeneratedValue
    private Long id;
    @JoinColumn(name = "examTakeInfoId")
    @ManyToOne(cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REMOVE})
    private ExamTakeInfo examTakeInfo;
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
                ", examTakeInfo='" + examTakeInfo + '\'' +
                ", examId='" + examId + '\'' +
                ", questionNumber=" + questionNumber +
                ", userAnswer=" + userAnswer +
                ", isAnswerCorrect=" + isAnswerCorrect +
                ", examTakeInfoId=" + getExamTakeInfo().getId() +
                '}';
    }


}



