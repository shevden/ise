package com.ds.ise.facade;

import com.ds.ise.data.dao.AnswerDAO;
import com.ds.ise.entity.Answer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AnswerFacadeImpl implements AnswerFacade {

    @EJB
    private AnswerDAO answerDAO;

    public void save(Answer answer) {
        answerDAO.save(answer);
    }

    public Answer update(Answer answer) {
        return answerDAO.update(answer);
    }

    public void delete(Answer answer) {
        answerDAO.delete(answer);
    }

    public Answer find(int entityID) {
        return answerDAO.find(entityID);
    }

    public List<Answer> findAll() {
        return answerDAO.findAll();
    }
}
