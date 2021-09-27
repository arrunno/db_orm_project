package dbProject.service;

//import dbProject.entity.ExamTake;


import dbProject.entity.ExamTake;
import dbProject.entity.Question;
import dbProject.entity.QuestionAnswer;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.Set;

public class ExamTakeService {
    private EntityManager em;
    private QuestionService qs;

    public ExamTakeService(EntityManager em) {

        this.em = em;
        this.qs = new QuestionService(em);
    }

    public ExamTake createExamTake(ExamTake examTake) {

        //// check if answer is correct
        int questionNumber = examTake.getQuestionNumber();
        String examId = examTake.getExamId();
        Question question = qs.getQuestionByExamIdAndNumber(examId, questionNumber);
        QuestionAnswer answerByNumber = getAnswerByNumber(question.getQuestionAnswers(), examTake.getUserAnswer());

        boolean isAnswerCorrect = (answerByNumber != null) ? answerByNumber.isAnswerCorrect() : false;
        examTake.setAnswerCorrect(isAnswerCorrect);

        em.persist(examTake);
        return examTake;
    }

    public void removeExamTake(Long id) {

        ExamTake examTake = em.find(ExamTake.class, id);
        if (examTake != null)
            em.remove(examTake);
    }

    public ExamTake findExamTake(Long id) {

        return em.find(ExamTake.class, id);
    }

    public static QuestionAnswer getAnswerByNumber(Set<QuestionAnswer> qas, int answerNumber)
    {
        Iterator<QuestionAnswer> it = qas.iterator();
        while (it.hasNext())
        {
            QuestionAnswer qa = it.next();
            if (qa.getAnswerNumber() != null && qa.getAnswerNumber() == answerNumber) {
                return qa;
            }
        }
        return null;
    }


}
