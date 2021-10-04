package dbProject.service;

//import dbProject.entity.ExamTake;


import dbProject.entity.*;
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

    public void getExamTakeStats(){

//        List<?> examQuestionStats =  em.createQuery("Select examId, Count(examId) as examTakes, Sum(numberOfCorrectAnswers) " +
//                "as numberOfCorrectAnswers, Sum(totalExamQuestions) as totalExamQuestions From ExamTakeInfo " +
//                        "Group by examId", ExamTakeInfo.class).getResultList();

        List<ExamTakeInfo> examQuestionStatsL =  em.createQuery("Select eti From ExamTakeInfo eti Order By examId, userEmail, examTakeDate", ExamTakeInfo.class).getResultList();

        for(ExamTakeInfo el : examQuestionStatsL) {
            System.out.println(el.toString());
        }
        return;
    }

    public void getExamStats(){
        List<ExamAnswer> examAnswersL =  em.createQuery("Select ea From ExamAnswer ea Order By examId, questionNumber, userAnswer", ExamAnswer.class).getResultList();
        for(ExamAnswer el : examAnswersL) {
            System.out.println(el.toString());
        }
        return;
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
        return;

    }


}
