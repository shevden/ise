package com.ds.ise.facade;

import com.ds.ise.entity.Answer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AnswerFacade {

    public abstract void save(Answer answer);

    public abstract Answer update(Answer answer);

    public abstract void delete(Answer answer);

    public abstract Answer find(int entityID);

    public abstract List<Answer> findAll();
}
