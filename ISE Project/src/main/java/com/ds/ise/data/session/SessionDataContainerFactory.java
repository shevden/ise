package com.ds.ise.data.session;

import com.ds.ise.data.global.RepositoryContainer;
import com.ds.ise.data.global.RepositoryContainerFactory;

import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class SessionDataContainerFactory {

    @EJB
    private RepositoryContainerFactory repositoryContainerFactory;

    @EJB
    private EntropyDataPreparator entropyDataPreparator;

    @EJB
    private ProbabilityDataPreparator probabilityDataPreparator;

    public SessionDataContainer newSessionDataContainer(){
        RepositoryContainer repositoryContainer = repositoryContainerFactory.getRepositoryContainer();
        SessionDataContainer result = new SessionDataContainer(repositoryContainer);
        entropyDataPreparator.prepareQuestionEntropy(result);
        probabilityDataPreparator.prepareItemProbability(result);

        return result;
    }

    public void prepareForReuse(SessionDataContainer sessionDataContainer){
        sessionDataContainer.clearAskedQuestions();
        entropyDataPreparator.prepareQuestionEntropy(sessionDataContainer);
        probabilityDataPreparator.prepareItemProbability(sessionDataContainer);
    }

}
