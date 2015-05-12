package com.ds.ise.data.session;

import com.ds.ise.entity.Question;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class EntropyDataPreparator {

    /**
     * Initializes container for info entropy of questions that may be asked.
     * Than adds all existing questions to the container.
     */
    public void prepareQuestionEntropy(SessionDataContainer sessionDataContainer) {
        Map<Question, Double> questionEntropies = sessionDataContainer.getQuestionEntropies();
        for (Question question : sessionDataContainer.getRepositoryContainer().getQuestions()) {
            questionEntropies.put(question, 0d);
        }
    }
}
