package dbProject.service;

//import dbProject.entity.ExamTake;


import dbProject.entity.ExamAnswer;
import dbProject.entity.ExamTakeInfo;
import dbProject.entity.Question;
import dbProject.entity.QuestionAnswer;
import dbProject.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ExamService {
    private EntityManager em;
    private QuestionService qs;

    public ExamService(EntityManager em) {

        this.em = em;
        this.qs = new QuestionService(em);
    }

    public ExamAnswer createExamAnswer(ExamTakeInfo examTakeInfo, ExamAnswer examAnswer) {

        //// check if answer is correct
        int questionNumber = examAnswer.getQuestionNumber();
        String examId = examAnswer.getExamId();
        Question question = qs.getQuestionByExamIdAndNumber(examId, questionNumber);
        QuestionAnswer answerByNumber = getAnswerByNumber(question.getQuestionAnswers(), examAnswer.getUserAnswer());

        boolean isAnswerCorrect = (answerByNumber != null) ? answerByNumber.isAnswerCorrect() : false;
        examAnswer.setAnswerCorrect(isAnswerCorrect);

        em.persist(examAnswer);
        return examAnswer;
    }
//Map<String, Integer>
    public void getExamTakeStats(String examId){

        List<?> dd = em.createQuery("Select distinct examTakeInfo.id, examId From ExamAnswer WHERE examId = : qExamId ").setParameter("qExamId","Geo_01").getResultList();

        List<?> examQuestionNums =  em.createQuery("Select Count(examId), examId as questionCount From Question Group By examId ").getResultList();

//        List<?> asdf = em.createQuery("select Count(et.examTakeInfo) As total_answers, etr.ra as right_answers, " +
//                        "et.examTakeInfo.id FROM ExamAnswer et left join (Select Count(et2.examTakeInfo.id) As ra, et2.examTakeInfo.id " +
//                        "FROM ExamAnswer et2 WHERE et2.isanswercorrect = true group by et2.examTakeInfo.id) as etr " +
//                "ON et.examTakeInfo.id = etr.examTakeInfo.id group by et.examTakeInfo.id, etr.ra).getResultList() ").getResultList();

        List<?> totalExamQuestions = em.createQuery("select COUNT(et.examTakeInfo) As total_questions, et.examTakeInfo.id FROM ExamAnswer et Group By et.examTakeInfo.id").getResultList(); // Stream().forEach(el->{

        Object ss = totalExamQuestions.get(0);

        String a = "";

/*
        select COUNT(et.exam_take_info_id) As total_answers, max(coalesce(etr.ra, 0)) as right_answers,
        et.exam_take_info_id FROM exam_takes et
        left join (Select COUNT(et2.exam_take_info_id) As ra, et2.exam_take_info_id
        FROM exam_takes et2 WHERE et2.isanswercorrect = true group by et2.exam_take_info_id) as etr
        ON et.exam_take_info_id = etr.exam_take_info_id
        group by et.exam_take_info_id, etr.ra
*/


    }


    public void removeExamTake(Long id) {

        ExamAnswer examAnswer = em.find(ExamAnswer.class, id);
        if (examAnswer != null)
            em.remove(examAnswer);
    }

    public ExamAnswer findExamTake(Long id) {

        return em.find(ExamAnswer.class, id);
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

    public void takeExam(Scanner sc, EntityTransaction tx, String examId, String userEmail, String examInfo, LocalDate date){
        QuestionService qs = new QuestionService(em);
        List<Question> questions = qs.getQuestionListByExamId(examId);

        int correctAnswers = 0;
        int totalquestions = 0;

        ExamTakeInfo eti = new ExamTakeInfo(examId, userEmail, examInfo, date);
        tx.begin();
        em.persist(eti);
        tx.commit();

        for (Question oneQs : questions){
            int questionNumber = oneQs.getQuestionNumber();
            totalquestions += 1;
            System.out.println(questionNumber + " " + oneQs.getQuestion());
            for (QuestionAnswer answer : oneQs.getQuestionAnswers()) {
                System.out.println("\t"+answer.getAnswerNumber() + " " + answer.getQuestionAnswer());
            }
            int userChoice = Utils.getInpIntOr0(sc, "");
            tx.begin();
            ExamAnswer eAn = new ExamAnswer(examId, questionNumber, userChoice);
            eAn.setExamTakeInfo(eti);
            this.createExamAnswer(eti, eAn);
            tx.commit();
            if (eAn.isAnswerCorrect()){
                correctAnswers += 1;
            }
        }
        System.out.println("Egzamino rezultatas: " + correctAnswers + " is " + totalquestions);

        eti.setNumberOfCorrectAnswers(correctAnswers);
        eti.setTotalExamQuestions(totalquestions);
        tx.begin();
            em.persist(eti);
        tx.commit();

    }


}
