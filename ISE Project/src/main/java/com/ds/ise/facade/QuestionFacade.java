package com.ds.ise.facade;

import com.ds.ise.entity.Question;

import javax.ejb.Local;
import java.util.List;

@Local
public interface QuestionFacade {

    public abstract void save(Question question);

    public abstract Question update(Question question);

    public abstract void delete(Question question);

    public abstract Question find(int entityID);

    public abstract List<Question> findAll();

}