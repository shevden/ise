package com.ds.ise.data.global;

import com.ds.ise.data.dao.AnswerDAO;
import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class AnswerDependenciesResolver {

    @EJB
    private AnswerDAO answerDAO;

    /**
     * This method is responsible for filling in {@code Answer} map,
     * if some entities represented by {@code Item} and {@code Question}
     * objects are not connected with each other.
     *
     * @param repositoryContainer that might be fulfilled.
     */
    public void resolveAnswerDependencies(RepositoryContainer repositoryContainer) {
        for (Question question : repositoryContainer.getQuestions()) {
            processQuestionDependencies(repositoryContainer, question);
        }
    }

    private void processQuestionDependencies(RepositoryContainer repositoryContainer, Question question) {
        List<Answer> answers = answerDAO.findByQuestionId(question.getId());
        Map<Item, Answer> itemAnswerMap = new LinkedHashMap<>();
        for (Item item : repositoryContainer.getItems()) {
            processItemDependenciesWithQuestion(question, answers, item, itemAnswerMap);
        }
        repositoryContainer.getAnswers().put(question, itemAnswerMap);
    }

    private void processItemDependenciesWithQuestion(Question question, List<Answer> answers,
                Item item, Map<Item, Answer> itemAnswerMap) {
        boolean isNoAnswer = true;
        boolean isItemsEqual;
        boolean isQuestionsEqual;
        for (Answer answer : answers) {
            isItemsEqual = answer.getItemId() == item.getId();
            isQuestionsEqual = answer.getQuestionId() == question.getId();
            if (isItemsEqual && isQuestionsEqual) {
                isNoAnswer = false;
                itemAnswerMap.put(item, answer);
                break;
            }
        }
        if (isNoAnswer) {
            addNewAnswer(question, itemAnswerMap, item);
        }
    }

    /**
     * Inserts default implementation for the Answer object, if
     * there is no one with existing Composition object and Question
     * object IDs.
     */
    private void addNewAnswer(Question question, Map<Item, Answer> itemAnswerMap, Item item) {
        Answer answer = Answer.newDefaultInstance(question.getId(), item.getId());
        answerDAO.save(answer);
        itemAnswerMap.put(item, answer);
    }
}
