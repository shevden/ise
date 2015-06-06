package com.ds.ise.data.dao;

import com.ds.ise.entity.Answer;
import com.ds.ise.entity.additional.AnswerKey;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AnswerDAO extends CommonDAO<Answer> {

    public static final String QUESTION_ID_FIELD = "questionId";

    public AnswerDAO() {
        super(Answer.class);
    }

    public void delete(Answer answer) {
        AnswerKey keyToDeleteBy = new AnswerKey(answer.getQuestionId(), answer.getItemId());
        super.delete(keyToDeleteBy);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Answer> findByQuestionId(long questionId){
        return findFilteredResults(QUESTION_ID_FIELD, questionId);
    }

}
