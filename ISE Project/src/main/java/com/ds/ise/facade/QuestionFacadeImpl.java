package com.ds.ise.facade;

import com.ds.ise.data.dao.QuestionDAO;
import com.ds.ise.entity.Question;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class QuestionFacadeImpl implements QuestionFacade {

    @EJB
    private QuestionDAO questionDAO;

    public void save(Question question) {
        questionDAO.save(question);
    }

    public Question update(Question question) {
        return questionDAO.update(question);
    }

    public void delete(Question question) {
        questionDAO.delete(question);
    }

    public Question find(int entityID) {
        return questionDAO.find(entityID);
    }

    public List<Question> findAll() {
        return questionDAO.findAll();
    }
}