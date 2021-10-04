package dbProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExamStats {
    private String examId;
    private Integer numberOfExamTakes;
    private Integer rightAnswerCount;
    private Integer totalAnswers;

}
