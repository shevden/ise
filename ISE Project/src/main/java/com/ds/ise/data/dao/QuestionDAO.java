package com.ds.ise.data.dao;

import com.ds.ise.entity.Question;

import javax.ejb.Stateless;

@Stateless
public class QuestionDAO extends CommonDAO<Question> {

public QuestionDAO() {
    super(Question.class);
}

public void delete(Question question) {
    super.delete(question.getId(), Question.class);
}

}