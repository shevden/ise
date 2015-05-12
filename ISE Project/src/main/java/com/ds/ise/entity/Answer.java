package com.ds.ise.entity;

import com.ds.ise.entity.additional.AnswerKey;
import com.ds.ise.entity.additional.AnswerType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Java Bean that represents answer on specified question about one item. This
 * is not an entity but a particular connection between every of them. This type
 * of connections must be inserted into the data structures and DB dynamically
 * only and by the monitoring system only. It must be unique both in
 * {@code questionId} and {@code itemId} fields. Every {@code Question} entity
 * must have a such connection object with every {@code Item} entity.
 *
 * @author Denis Shevchenko
 * @version 1.0 (alpha)
 */
@Entity
@IdClass(AnswerKey.class)
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @Column(name = "question_id")
    private long questionId;

    @Id
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "positive")
    private long positive;

    @Column(name = "negative")
    private long negative;

    @Column(name = "pr_positive")
    private long probablePositive;

    @Column(name = "pr_negative")
    private long probableNegative;

    @Column(name = "dont_know")
    private long dontKnow;

    @Column(name = "asks")
    private long asks;

    public Answer(){}

    /**
     * Sets up default state for new combination of {@code Question} and
     * {@code Item} objects. {@literal 1} must be set up to all fields,
     * because state of object must guarantee, that there will not be division
     * by zero. So all types of answer should be equal at first.
     *
     * @param questionId of {@code Question} object that will be connected with
     *                   particular {@code Person}.
     * @param itemId   of {@code Person} object that will be connected with
     *                   particular {@code Question}.
     * @return {@code Answer} object, that represents connection between
     * particular {@code Question} and {@code Person} objects.
     */
    public static Answer newDefaultInstance(long questionId, long itemId) {
        Answer answer = new Answer();
        answer.questionId = questionId;
        answer.itemId = itemId;
        answer.positive = 1;
        answer.negative = 1;
        answer.probablePositive = 1;
        answer.probableNegative = 1;
        answer.dontKnow = 1;
        answer.asks = 1;

        return answer;
    }


    public long getOptionRequestsValue(AnswerType answerType) {
        switch (answerType) {
            case POSITIVE:
                return getPositive();
            case NEGATIVE:
                return getNegative();
            case PROBABLE_POSITIVE:
                return getProbablePositive();
            case PROBABLE_NEGATIVE:
                return getProbableNegative();
            case DONT_KNOW:
                return getDontKnow();
        }

        throw new IllegalArgumentException("Cannot find specified option.");
    }

    /**
     * Increments number that indicates how many times the particular answer was
     * given to the particular {@code Question} about particular {@code Item}.
     *
     * @param answerType defines: what kind of answers must be returned.
     */
    public synchronized void incrementOptionRequestsValue(AnswerType answerType) {
        switch (answerType) {
            case POSITIVE:
                ++positive;
            case NEGATIVE:
                ++negative;
            case PROBABLE_POSITIVE:
                ++probablePositive;
            case PROBABLE_NEGATIVE:
                ++probableNegative;
            case DONT_KNOW:
                ++dontKnow;
        }
    }

    /**
     * Increments number that indicates how many times the particular
     * {@code Question} was asked about particular {@code Item}.
     */
    public synchronized void incrementAsks() {
        ++asks;
    }

    @Override
    public String toString() {
        return new StringBuilder("Answer [questionId=").append(getQuestionId())
                .append(", itemId=").append(getItemId())
                .append(", positive=").append(getPositive())
                .append(", negative=").append(getNegative())
                .append(", probablePositive=").append(getProbablePositive())
                .append(", probableNegative=").append(getProbableNegative())
                .append(", dontKnow=").append(getDontKnow()).append(", asks=")
                .append(getAsks()).append("]").toString();
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getPositive() {
        return positive;
    }

    public void setPositive(long positive) {
        this.positive = positive;
    }

    public long getNegative() {
        return negative;
    }

    public void setNegative(long negative) {
        this.negative = negative;
    }

    public long getProbablePositive() {
        return probablePositive;
    }

    public void setProbablePositive(long probablePositive) {
        this.probablePositive = probablePositive;
    }

    public long getProbableNegative() {
        return probableNegative;
    }

    public void setProbableNegative(long probableNegative) {
        this.probableNegative = probableNegative;
    }

    public long getDontKnow() {
        return dontKnow;
    }

    public void setDontKnow(long dontKnow) {
        this.dontKnow = dontKnow;
    }

    public long getAsks() {
        return asks;
    }

    public void setAsks(long asks) {
        this.asks = asks;
    }

}
