package dbProject.service;

import dbProject.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Arunas Tamosevicius
 */
public class QuestionService {


  private EntityManager em;

  public QuestionService(EntityManager em) {
    this.em = em;
  }

  public Question createQuestion(Question question) {

    em.persist(question);
      return question;
  }

  public void removeQuestion(Long id) {

    Question question = em.find(Question.class, id);
      if (question != null)
          em.remove(question);
  }

  public Question findQuestion(Long id) {

      return em.find(Question.class, id);
  }

  public Question getQuestionByExamIdAndNumber(String examId, int questionNumber){

    Query query = em.createQuery("FROM Question q WHERE q.examId=:qExamId AND questionNumber=:qQuestionNumber", Question.class);
    query.setParameter("qExamId", examId);
    query.setParameter("qQuestionNumber", questionNumber);
    return (Question) query.getSingleResult();
  }

  public List<Question> getQuestionListByExamId(String examId){

    Query query = em.createQuery("FROM Question q WHERE q.examId=:qExamId Order By QuestionNumber Asc", Question.class);
    query.setParameter("qExamId", examId);
    return query.getResultList();
  }

}