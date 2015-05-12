package com.ds.ise.data.global;

import com.ds.ise.entity.Answer;
import com.ds.ise.entity.Item;
import com.ds.ise.entity.Question;

import java.util.List;
import java.util.Map;

public class RepositoryContainer {

    private List<Question> questions;
    private List<Item> items;
    private Map<Question, Map<Item, Answer>> answersMap;

    public RepositoryContainer(List<Question> questions, List<Item> items,
                               Map<Question, Map<Item, Answer>> answersMap) {
        this.questions = questions;
        this.items = items;
        this.answersMap = answersMap;
    }

    /**
     * This instance of {@code List<Question> } is shared by multiple users
     * whenever they need it while play/teach mode. Method provides synchronized
     * access.
     *
     * @return shared instance of {@code List<Question> }.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * This instance of {@code List<Item>} is shared by multiple users
     * whenever they need it while search/play/teach mode. Method provides synchronized
     * access.
     *
     * @return shared instance of {@code List<Item>}.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * This instance of {@code Map<Question, Map<Item,Answer>>} is shared by
     * multiple users whenever they need it while play/teach mode. Method
     * provides synchronized access.
     *
     * @return shared instance of {@code Map<Question, Map<Item,Answer>>}.
     */
    public Map<Question, Map<Item, Answer>> getAnswers() {
        return answersMap;
    }

}
