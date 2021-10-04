package dbProject.utils;

import dbProject.PersistenceManager;
import dbProject.entity.ExamAnswer;
import dbProject.entity.ExamTakeInfo;
import dbProject.entity.Question;
import dbProject.entity.QuestionAnswer;
import dbProject.service.ExamService;
import dbProject.service.QuestionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DataActions {

    public static void fill(){

        EntityManager em = PersistenceManager.instance.getentitymanager();
        EntityTransaction tx = em.getTransaction();
        QuestionService service = new QuestionService(em);

        tx.begin();
            Set<QuestionAnswer> answers = new HashSet<>();
            answers.add(new QuestionAnswer(1, "Bogota", false));
            answers.add(new QuestionAnswer(2, "Antananaryvas", false));
            answers.add(new QuestionAnswer(3, "Karakasas", true));
            Question q1 = new Question("Geo_01",1,"Kas yra Venesuelos sostine?");
            q1.setQuestionAnswers(answers);
            q1 = service.createQuestion(q1);
        tx.commit();

        tx.begin();
            Set<QuestionAnswer> answers2 = new HashSet<>();
            answers2.add(new QuestionAnswer(1, "Nilas", true));
            answers2.add(new QuestionAnswer(2, "Amazone", false));
            answers2.add(new QuestionAnswer(3, "Jenisiejus", false));
            Question q2 = new Question("Geo_01",2,"Kokia pasaulio upe yra ilgiausia?");
            q2.setQuestionAnswers(answers2);
            q2 = service.createQuestion(q2);
        tx.commit();

        tx.begin();
            Set<QuestionAnswer> answers3 = new HashSet<>();
            answers3.add(new QuestionAnswer(1, "Madagaskaras", false));
            answers3.add(new QuestionAnswer(2, "Borneo", true));
            answers3.add(new QuestionAnswer(3, "Sumatra", false));
            Question q3 = new Question("Geo_01",3,"Kuri iš šių salų didžiausia?");
            q3.setQuestionAnswers(answers3);
            q3 = service.createQuestion(q3);
        tx.commit();

        tx.begin();
        Set<QuestionAnswer> answers4 = new HashSet<>();
        answers4.add(new QuestionAnswer(1, "Marijampole", false));
        answers4.add(new QuestionAnswer(2, "Alytus", true));
        answers4.add(new QuestionAnswer(3, "Kazlu Ruda", false));
        Question q4 = new Question("Geo_02",1,"Kas yra Dzukijos sostine?");
        q4.setQuestionAnswers(answers4);
        q4 = service.createQuestion(q4);
        tx.commit();

        tx.begin();
        Set<QuestionAnswer> answers5 = new HashSet<>();
        answers5.add(new QuestionAnswer(1, "Druksiai", false));
        answers5.add(new QuestionAnswer(2, "Sartai", false));
        answers5.add(new QuestionAnswer(3, "Tauragnas", true));
        Question q5 = new Question("Geo_02",2,"Koks yra giliausias Lietuvos ezeras?");
        q5.setQuestionAnswers(answers5);
        q5 = service.createQuestion(q5);
        tx.commit();

        tx.begin();
        Set<QuestionAnswer> answers6 = new HashSet<>();
        answers6.add(new QuestionAnswer(1, "Mazeikiai", false));
        answers6.add(new QuestionAnswer(2, "Birzai", false));
        answers6.add(new QuestionAnswer(3, "Naujoji Akmene", true));
        Question q6 = new Question("Geo_02",3,"Koks Lietuvos miestas yra šiauriausias?");
        q6.setQuestionAnswers(answers6);
        q6 = service.createQuestion(q6);
        tx.commit();

//        Set<QuestionAnswer> upes = q2.getQuestionAnswers();
//        System.out.println("Originalus atsakymu setas: " + upes);
        //// DELETE
//        answerRemove(upes,3);
//        answerRemove(upes,4);

//        System.out.println("Pakoreguotas atsakymu setas: " + upes);

//        tx.begin();
//            q2.setQuestionAnswers(upes);
//            q2 = service.createQuestion(q2);
//        tx.commit();

        //// UPDATE
//        tx.begin();
//            updateAnswerSet(upes,3, "Jandze", 2, "Geo_01");
//            q2 = service.createQuestion(q2);
//        tx.commit();



        // Finds entity by primary key
//        q1 = service.findQuestion(q1.getId());
//
//        System.out.println("Question Found     : " + q1);
//        System.out.println("   Answers : " + q1.getQuestionAnswers());

        // Removes entity
//        tx.begin();
//        service.removeQuestion(q1.getId());
//        tx.commit();

//        System.out.println("Entity Removed");

        // Finds Entity by primary key
//        q1 = service.findQuestion(q1.getId());
//
//        System.out.println("Entity Not Found : " + q1);

        //// EXAM TAKES creation
        ExamService ets = new ExamService(em);

        tx.begin();
            ExamTakeInfo eti = new ExamTakeInfo("Geo_01", "arunas@student.com", "Pasaulio geografija 1", LocalDate.of(2021,9,27));
            ExamAnswer et1 = new ExamAnswer("Geo_01", 1, 3);
            et1.setExamTakeInfo(eti);
            ets.createExamAnswer(eti, et1);
        tx.commit();
        tx.begin();
            ExamAnswer et2 = new ExamAnswer("Geo_01", 2, 1);
            et2.setExamTakeInfo(eti);
            ets.createExamAnswer(eti, et2);
        tx.commit();
        tx.begin();
            ExamAnswer et3 = new ExamAnswer("Geo_01", 3, 2);
            et3.setExamTakeInfo(eti);
            ets.createExamAnswer(eti, et3);
        tx.commit();
        tx.begin();
            eti.setTotalExamQuestions(3);
            eti.setNumberOfCorrectAnswers(3);
            em.persist(eti);
        tx.commit();

        tx.begin();
            ExamTakeInfo eti2 = new ExamTakeInfo("Geo_01", "ona@student.com", "Pasaulio geografija 1", LocalDate.of(2021,9,30));
            ExamAnswer et4 = new ExamAnswer("Geo_01", 1, 1);
            et4.setExamTakeInfo(eti2);
            ets.createExamAnswer(eti2, et4);
        tx.commit();

        tx.begin();
            ExamAnswer et5 = new ExamAnswer("Geo_01", 2, 3);
            et5.setExamTakeInfo(eti2);
            ets.createExamAnswer(eti2, et5);
            tx.commit();

        tx.begin();
            ExamAnswer et6 = new ExamAnswer("Geo_01", 3, 2);
            et6.setExamTakeInfo(eti2);
            ets.createExamAnswer(eti2, et6);
        tx.commit();
        tx.begin();
            eti2.setTotalExamQuestions(3);
            eti2.setNumberOfCorrectAnswers(1);
            em.persist(eti2);
        tx.commit();

        tx.begin();
            Set<QuestionAnswer> answers7 = new HashSet<>();
            answers7.add(new QuestionAnswer(1, "Juozapines", false));
            answers7.add(new QuestionAnswer(2, "Medvegalio", false));
            answers7.add(new QuestionAnswer(3, "Aukstojas", true));
            Question q7 = new Question("Geo_02",4,"Kokia Lietuvos kalva yra auksciausia?");
            q7.setQuestionAnswers(answers7);
            service.createQuestion(q7);
        tx.commit();
    }



}
