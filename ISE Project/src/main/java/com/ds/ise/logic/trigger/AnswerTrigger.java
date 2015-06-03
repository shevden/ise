package com.ds.ise.logic.trigger;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Item;

import javax.ejb.Stateless;

import static java.util.Map.Entry;

@Stateless
public class AnswerTrigger {

    public static final int PROBABILITY_TRIGGERING_DIFF = 10;
    public static final int SUSPEND_RESULT_ALERT_LIMIT = 10;

    public boolean refreshReadinessState(SessionDataContainer sessionDataContainer){
        double maxProbability = -1;
        double secondProbability = -1;
        Item maxProbabilityItem = null;

        for(Entry<Item, Double> itemProbability:
                sessionDataContainer.getItemProbabilities().entrySet()){
            double iterableProbability = itemProbability.getValue();
            if(iterableProbability > secondProbability){
                if(iterableProbability > maxProbability){
                    secondProbability = maxProbability;
                    maxProbability = iterableProbability;
                    maxProbabilityItem = itemProbability.getKey();
                } else {
                    secondProbability = iterableProbability;
                }
            }
        }
        if(maxProbability > secondProbability * PROBABILITY_TRIGGERING_DIFF ||
                sessionDataContainer.getNumberOfQuestionsAsked() > SUSPEND_RESULT_ALERT_LIMIT
                * sessionDataContainer.getSearchRoundNumber()){
                sessionDataContainer.setResultItem(maxProbabilityItem);
             return true;
        }

        return false;
    }

}
