package com.ds.ise.entity.additional;


import java.io.Serializable;

public class AnswerKey implements Serializable{

    public long questionId;
    public long itemId;

    public AnswerKey(){}

    public AnswerKey(long questionId, long itemId){
        this.questionId = questionId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnswerKey)) {
            return false;
        }
        AnswerKey answerKey = (AnswerKey) other;

        return questionId == answerKey.questionId && itemId == answerKey.itemId;
    }

    @Override
    public int hashCode() {
        int result = (int) (questionId ^ (questionId >>> 32));
        result = 31 * result + (int) (itemId ^ (itemId >>> 32));

        return result;
    }
}
