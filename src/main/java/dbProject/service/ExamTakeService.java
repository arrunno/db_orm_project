package dbProject.service;

//import dbProject.entity.ExamTake;


import dbProject.entity.ExamTake;
import dbProject.entity.ExamTakeInfo;
import dbProject.entity.Question;
import dbProject.entity.QuestionAnswer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExamTakeService {
    private EntityManager em;
    private QuestionService qs;

    public ExamTakeService(EntityManager em) {

        this.em = em;
        this.qs = new QuestionService(em);
    }

    public ExamTake createExamTake(ExamTakeInfo examTakeInfo,  ExamTake examTake) {

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
//Map<String, Integer>
    public void getExamTakeStats(String examId){

        List<?> dd = em.createQuery("Select distinct examTakeInfo.id, examId From ExamTake WHERE examId = : qExamId ").setParameter("qExamId","Geo_01").getResultList();

        List<?> examQuestionNums =  em.createQuery("Select Count(examId), examId as questionCount From Question Group By examId").getResultList();

        List<?> totalExamQuestions = em.createQuery("select COUNT(et.examTakeInfo) As total_questions, et.examTakeInfo.id FROM ExamTake et Group By et.examTakeInfo.id").getResultList(); // Stream().forEach(el->{




         Object ss = totalExamQuestions.get(0);

                ;
//        et.exam_take_info_id FROM exam_takes et


        String a = "";
                //.setParameter("qExamId", examId);
//        final List<?> qExamId = qExamId1.getResultList();

/*
        select COUNT(et.exam_take_info_id) As total_answers, max(coalesce(etr.ra, 0)) as right_answers,
        et.exam_take_info_id FROM exam_takes et
        left join (Select COUNT(et2.exam_take_info_id) As ra, et2.exam_take_info_id
        FROM exam_takes et2 WHERE et2.isanswercorrect = true group by et2.exam_take_info_id) as etr
        ON et.exam_take_info_id = etr.exam_take_info_id
        group by et.exam_take_info_id, etr.ra
*/

//        List<Long> examTakesIds
//        return null;
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
