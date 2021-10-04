package dbProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arunas Tamosevicius
 */

@Setter
@Getter
@Entity
@Table(name = "questions", uniqueConstraints = @UniqueConstraint(columnNames = {"examId", "questionNumber"}))
  public class Question implements Serializable {

  @Id
  @GeneratedValue
  protected Long id;
  private String examId;
  private Integer questionNumber;
  private String question;

  @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumns({
          @JoinColumn(name="examId", referencedColumnName = "examId"),
          @JoinColumn(name="questionNumber", referencedColumnName = "questionNumber")
  })
  @OrderBy("answerNumber")
  private Set<QuestionAnswer> questionAnswers = new HashSet<>();

  public Question() {
  }

  public Question(String examId) {
    this.examId = examId;
  }

  public Question(String examId, int questionNumber,  String question) {
    this.examId = examId;
    this.questionNumber = questionNumber;
    this.question = question;
  }

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", examId='" + examId + '\'' +
            ", questionNumber=" + questionNumber +
            ", question='" + question + '\'' +
            ", questionAnswers=" + questionAnswers +
            '}';
  }

}