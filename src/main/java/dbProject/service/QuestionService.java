package dbProject.service;

import dbProject.entity.Question;

import javax.persistence.EntityManager;

/**
 * @author Arunas Tamosevicius
 */
public class QuestionService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public QuestionService(EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

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
}