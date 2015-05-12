package com.ds.ise.data.session;

import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.entity.Item;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful
public class ProbabilityDataPreparator {

    @EJB
    private ItemDAO itemDAO;

    /**
     * Initializes container for probabilities of items been searched for.
     * Than adds all existing items to the container, calculates a prior
     * probability for each of them and puts it in container, too.
     */
    public void prepareItemProbability(SessionDataContainer sessionDataContainer) {
        Map<Item, Double> itemProbabilities = sessionDataContainer.getItemProbabilities();
        double totalRequests = itemDAO.getTotalRequestsNumber();
        for (Item item : sessionDataContainer.getRepositoryContainer().getItems()) {
            itemProbabilities.put(item, ((double) item.getRequests()) / totalRequests);
        }
    }
}
