package com.ds.ise.logic.calculator;


import com.ds.ise.data.global.RepositoryContainer;
import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;
import com.ds.ise.entity.additional.AnswerType;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class ProbabilityCalculator {

    public static final int ONE_PROBABILITY_LIMIT = 1;
    public static final int PROBABILITY_INCREASE_STEP = 10;

    /**
     * Calculates probabilities of items been searched for, and stores them at
     * {@code personProbabilities}. Besides that, pints out every person from
     * mentioned container with his probability.
     */
    public void recalculateProbability(SessionDataContainer sessionDataContainer) {
        Question lastAskedQuestion = sessionDataContainer.getLastAskedQuestion();
        RepositoryContainer repositoryContainer = sessionDataContainer.getRepositoryContainer();
        Map<Item, Answer> itemsAnswers = repositoryContainer.getAnswers().get(lastAskedQuestion);
        AnswerType answerType = sessionDataContainer.getQuestionsAnswers().get(lastAskedQuestion);
        recalculateProbability(sessionDataContainer, itemsAnswers, answerType);
    }

    private void recalculateProbability(SessionDataContainer sessionDataContainer,
                                        Map<Item, Answer> itemsAnswers, AnswerType answerType) {
        boolean isAllBelowOne = true;
        for(Double probability: sessionDataContainer.getItemProbabilities().values()){
            if(probability > ONE_PROBABILITY_LIMIT){
                isAllBelowOne = false;
                break;
            }
        }
        for (Item item : itemsAnswers.keySet()) {
            recalculateItemProbability(sessionDataContainer, itemsAnswers, answerType, isAllBelowOne, item);
        }
    }

    private void recalculateItemProbability(SessionDataContainer sessionDataContainer,
                                            Map<Item, Answer> itemsAnswers, AnswerType answerType,
                                            boolean isAllBelowOne, Item item) {
        Answer answer = itemsAnswers.get(item);
        double oldValue = sessionDataContainer.getItemProbabilities().get(item);
        double deltaValue = answer.getOptionRequestsValue(answerType) / answer.getAsks();
        double newValue = oldValue * deltaValue;
        if(isAllBelowOne){
            newValue *= PROBABILITY_INCREASE_STEP;
        }
        sessionDataContainer.getItemProbabilities().put(item, newValue);
    }
}
