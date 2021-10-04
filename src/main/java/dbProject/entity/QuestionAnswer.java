package dbProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Arunas Tamosevicius
 */
@Setter
@Getter
@Entity
@Table(name="question_answers")
public class QuestionAnswer {

  @Id
  @GeneratedValue
  private Long id;

  private String examId;
  private Integer questionNumber;
  private Integer answerNumber;
  private String questionAnswer;
  private boolean isAnswerCorrect;

  public QuestionAnswer() {
  }

  public QuestionAnswer(int answerNumber, String questionAnswer, boolean isAnswerCorrect) {
    this.answerNumber = answerNumber;
    this.questionAnswer = questionAnswer;
    this.isAnswerCorrect = isAnswerCorrect;
  }

  @Override
  public String toString() {
    return "QuestionAnswer{" +
            "id=" + id +
            ", examId='" + examId + '\'' +
            ", questionNumber=" + questionNumber +
            ", answerNumber=" + answerNumber +
            ", questionAnswer='" + questionAnswer + '\'' +
            ", isAnswerCorrect=" + isAnswerCorrect +
            '}';
  }

}