package com.ds.ise.logic.calculator;


import com.ds.ise.data.global.RepositoryContainer;
import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;
import com.ds.ise.entity.additional.AnswerType;
import com.ds.ise.logic.math.LogarithmMathService;

import javax.ejb.Stateless;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Stateless
public class EntropyCalculator {

    /**
     * Searches for the smallest info entropy in {@code questionEntropies}.
     * Besides that, pints out every question from mentioned with it's entropy.
     *
     * @return {@code Question} that has the most appropriate value of the info
     *         entropy at the current state of {@code questionEntropies}.
     */
    public Question getMinEntropyQuestion(SessionDataContainer sessionDataContainer) {
        Set<Map.Entry<Question, Double>> entrySet = sessionDataContainer.getQuestionEntropies().entrySet();
        Map.Entry<Question, Double> min = null;
        for (Map.Entry<Question, Double> questionEntropy : entrySet) {
            if (min == null || min.getValue() > questionEntropy.getValue()) {
                min = questionEntropy;
            }
        }
        if (min == null){
            throw new NullPointerException("No questions to process.");
        }

        return min.getKey();
    }

    /**
     * Calculates info entropy for each {@code Question}, that exists in
     * {@code questionEntropies}. Then puts it in this {@code Map}.
     */
    public void recalculateEntropy(SessionDataContainer sessionDataContainer) {
        for (Question question : sessionDataContainer.getQuestionEntropies().keySet()) {
            recalculateQuestionEntropy(sessionDataContainer, question);
        }
    }

    private void recalculateQuestionEntropy(SessionDataContainer sessionDataContainer, Question question) {
        double entropy = 0;
        RepositoryContainer repositoryContainer = sessionDataContainer.getRepositoryContainer();
        Map<Item, Answer> itemsAnswers = repositoryContainer.getAnswers().get(question);
        Iterator<Map.Entry<Item, Answer>> answerIterator;
        Answer iterable;
        Item current = null;
        for (AnswerType answerType : AnswerType.values()) {
            answerIterator = itemsAnswers.entrySet().iterator();
            double fullProbability = 0;
            for (Double compositionProbability : sessionDataContainer.getItemProbabilities().values()) {
                Map.Entry<Item, Answer> toProcess = answerIterator.next();
                iterable = toProcess.getValue();
                current = toProcess.getKey();
                double optionRequestsValue = iterable.getOptionRequestsValue(answerType);
                fullProbability += optionRequestsValue / iterable.getAsks() * compositionProbability;
            }
            double currentProbabilityMulFull = sessionDataContainer.getItemProbabilities().get(current) * fullProbability;
            entropy += -LogarithmMathService.lb(currentProbabilityMulFull) * fullProbability;
        }
        sessionDataContainer.getQuestionEntropies().put(question, entropy);
    }

}
