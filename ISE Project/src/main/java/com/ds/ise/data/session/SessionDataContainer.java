package com.ds.ise.data.session;


import com.ds.ise.data.global.RepositoryContainer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;
import com.ds.ise.entity.additional.AnswerType;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionDataContainer {

    private RepositoryContainer repositoryContainer;
    private Map<Question, AnswerType> questionsAnswers;
    private Map<Item, Double> itemProbabilities;
    private Map<Question, Double> questionEntropies;
    private Question lastAskedQuestion;

    public SessionDataContainer(RepositoryContainer repositoryContainer){
        this.repositoryContainer = repositoryContainer;
        questionsAnswers = new LinkedHashMap<>();
        itemProbabilities = new LinkedHashMap<>();
        questionEntropies = new LinkedHashMap<>();
    }

    public void addAnswerToQuestion(AnswerType answer){
        questionsAnswers.put(lastAskedQuestion, answer);
    }

    public void nullifyLastAskedQuestion(){
        lastAskedQuestion = null;
    }

    public void removeIndexedQuestion(Question question){
        questionEntropies.remove(question);
    }

    public void clearAskedQuestions(){
        questionsAnswers.clear();
        lastAskedQuestion = null;
    }

    public RepositoryContainer getRepositoryContainer() {
        return repositoryContainer;
    }

    public Map<Question, AnswerType> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public Map<Item, Double> getItemProbabilities() {
        return itemProbabilities;
    }

    public Map<Question, Double> getQuestionEntropies() {
        return questionEntropies;
    }

    public Question getLastAskedQuestion(){
        return lastAskedQuestion;
    }

    public void setLastAskedQuestion(Question lastAskedQuestion){
        this.lastAskedQuestion = lastAskedQuestion;
    }

}
