package dbProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Arunas Tamosevicius
 */
@Setter
@Getter
@Entity
public class QuestionAnswer {

  @Id
  @GeneratedValue
  private Long id;

  private String examId;
  private Integer questionNumber;
  private Integer answerNumber;
  private String questionAnswer;
  private boolean isAnswerTrue;

  public QuestionAnswer() {
  }

  public QuestionAnswer(int answerNumber, String questionAnswer, boolean isAnswerTrue) {
    this.answerNumber = answerNumber;
    this.questionAnswer = questionAnswer;
    this.isAnswerTrue = isAnswerTrue;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("QuestionAnswer{");
    sb.append("id=").append(id).append('\'');
    sb.append("examId=").append(examId).append('\'');
    sb.append("questionNumber=").append(questionNumber).append('\'');
    sb.append("answerNumber=").append(answerNumber).append('\'');
    sb.append(", answer='").append(questionAnswer).append('\'');
    sb.append('}');
    return sb.toString();
  }
}