package dbProject;

import dbProject.entity.Question;
import dbProject.entity.QuestionAnswer;
import dbProject.service.QuestionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to DB Project");

        EntityManager em = PersistenceManager.instance.getentitymanager();
        EntityTransaction tx = em.getTransaction();
        QuestionService service = new QuestionService(em);

        // Creates and persists an entity
        tx.begin();
        Set<QuestionAnswer> answers = new HashSet<>();
        answers.add(new QuestionAnswer(1, "Bogota", false));
        answers.add(new QuestionAnswer(2, "Antananaryvas", false));
        answers.add(new QuestionAnswer(3, "Karakasas", true));
        answers.add(new QuestionAnswer(4, "Belizas", false));
        Question q1 = new Question("Geo01",1,"Kas yra Venesuelos sostine?");
        q1.setQuestionAnswers(answers); // setMusicians(answers);
        q1 = service.createQuestion(q1);
        tx.commit();
        System.out.println("Question Persisted : " + q1);

        //// CREATE
        tx.begin();
        Set<QuestionAnswer> answers2 = new HashSet<>();
        answers2.add(new QuestionAnswer(1, "Nilas", true));
        answers2.add(new QuestionAnswer(2, "Amazone", false));
        answers2.add(new QuestionAnswer(3, "Jenisiejus", false));
        answers2.add(new QuestionAnswer(4, "Volga", false));
        Question q2 = new Question("Geo01",2,"Kokia pasaulio upe yra ilgiausia?");
        q2.setQuestionAnswers(answers2); // setMusicians(answers2);
        q2 = service.createQuestion(q2);
        tx.commit();

        Set<QuestionAnswer> upes = q2.getQuestionAnswers();
        System.out.println("Originalus atsakymu setas: " + upes);

        //// DELETE
        answerRemove(upes,3);
//        answerRemove(upes,4);

        System.out.println("Pakoreguotas atsakymu setas: " + upes);

        tx.begin();
            q2.setQuestionAnswers(upes);
            q2 = service.createQuestion(q2);
        tx.commit();

        //// UPDATE
        tx.begin();
            updateAnswerSet(upes,4, "Jandze", 2, "Geo01");
//            qa.setQuestionAnswer("Rio Grande");
//            upes.add(qa);
//            q2.setQuestionAnswers(upes);
            q2 = service.createQuestion(q2);
        tx.commit();



        System.out.println("Question Persisted : " + q2);

        // Finds entity by primary key
        q1 = service.findQuestion(q1.getId());

        System.out.println("Question Found     : " + q1);
        System.out.println("   Answers : " + q1.getQuestionAnswers());

        // Removes entity
        tx.begin();
        service.removeQuestion(q1.getId());
        tx.commit();

        System.out.println("Entity Removed");

        // Finds Entity by primary key
        q1 = service.findQuestion(q1.getId());

        System.out.println("Entity Not Found : " + q1);

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
