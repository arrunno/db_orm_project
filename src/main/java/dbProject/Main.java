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

        //// PrefillDB
        DataActions.fill();

        //// Actions
        Scanner sc = new Scanner(System.in);
        Gui gui = new Gui(em, tx);
        gui.startGUI(sc);

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
