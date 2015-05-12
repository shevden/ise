package com.ds.ise.data.global;

import com.ds.ise.data.dao.AnswerDAO;
import com.ds.ise.data.dao.ItemDAO;
import com.ds.ise.data.dao.QuestionDAO;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RepositoryContainerFactory {

    @EJB
    private QuestionDAO questionDAO;

    @EJB
    private ItemDAO itemDAO;

    @EJB
    private AnswerDAO answerDAO;

    @EJB
    private AnswerDependenciesResolver answerDependenciesResolver;

    public RepositoryContainer getRepositoryContainer(){
        List<Question> questions = questionDAO.findAll();
        List<Item> items = itemDAO.findAll();
        Map<Question, Map<Item, Answer>> answersMap = new LinkedHashMap<>();
        RepositoryContainer result = new RepositoryContainer(questions, items, answersMap);
        answerDependenciesResolver.resolveAnswerDependencies(result);

        return result;
    }
}
