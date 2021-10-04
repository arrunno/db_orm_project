package dbProject;

import dbProject.entity.QuestionAnswer;
import dbProject.gui.Gui;
import dbProject.service.ExamService;
import dbProject.utils.DataActions;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to DB Project");
        EntityManager em = PersistenceManager.instance.getentitymanager();
        EntityTransaction tx = em.getTransaction();

//        QuestionService service = new QuestionService(em);

//        QuestionService qs = new QuestionService(em);
//        List<Question> questions = qs.getQuestionListByExamId("Geo_02");
//
//        Scanner sc = new Scanner(System.in);
//        LocalDate date = LocalDate.now();
//        String examId = "Geo_02";
//        String userEmail = "arunas@student.com";
//        String examInfo = "Lietuvos geografija 1";
//        int correctAnswers = 0;
//        int totalquestions = 0;
//
//
//
//        ExamTakeInfo eti = new ExamTakeInfo(examId, userEmail, examInfo, date);
//        tx.begin();
//            em.persist(eti);
//        tx.commit();
//
//        for (Question oneQs : questions){
//            int questionNumber = oneQs.getQuestionNumber();
//            totalquestions += 1;
//            System.out.println(questionNumber + " " + oneQs.getQuestion());
//            for (QuestionAnswer answer : oneQs.getQuestionAnswers()) {
//                System.out.println("\t"+answer.getAnswerNumber() + " " + answer.getQuestionAnswer());
//            }
//            int userChoice = utils.getInpIntOr0(sc, "");
//            tx.begin();
//                ExamAnswer eAn = new ExamAnswer(examId, questionNumber, userChoice);
//                eAn.setExamTakeInfo(eti);
//                es.createExamAnswer(eti, eAn);
//            tx.commit();
//            if (eAn.isAnswerCorrect()){
//                correctAnswers += 1;
//            }
//        }
//
//        eti.setNumberOfCorrectAnswers(correctAnswers);
//        eti.setTotalExamQuestions(totalquestions);
//        tx.begin();
//            em.persist(eti);
//        tx.commit();
//
//        System.out.println("corr ans " + correctAnswers);
//        System.out.println("Total questions in exam " + totalquestions);

        DataActions.fill();

        Scanner sc = new Scanner(System.in);

        Gui gui = new Gui(em, tx);
        gui.startGUI(sc);


//        ExamService es = new ExamService(em);
//        LocalDate date = LocalDate.of(2021, 8, 14);
//        String examId = "Geo_02";
//        String userEmail = "petras@student.com";
//        String examInfo = "Lietuvos geografija 1";
//        es.takeExam(sc, tx, examId, userEmail, examInfo, date);

        em.close();




    }

    public static void answerRemove(Set<QuestionAnswer> qas, int answerToRemove)
    {
        Iterator<QuestionAnswer> it = qas.iterator();
        while (it.hasNext())
        {
            Integer qaNow = it.next().getAnswerNumber();
            if (qaNow != null && qaNow == answerToRemove) {
                it.remove();
            }
        }
    }

    public static void updateAnswerSet(Set<QuestionAnswer> qas, int answerToCorrect, String correctedAnswer, int questionNumber, String examId)
    {
        Iterator<QuestionAnswer> it = qas.iterator();
        while (it.hasNext())
        {
            QuestionAnswer qa = it.next();
            Integer qaNow = qa.getAnswerNumber();
            if (qaNow != null && qaNow == answerToCorrect) {
                qa.setQuestionNumber(questionNumber);
                qa.setExamId(examId);
                qa.setQuestionAnswer(correctedAnswer);
                return;
            }
        }
    }
}
