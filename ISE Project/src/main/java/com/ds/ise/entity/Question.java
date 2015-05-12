package com.ds.ise.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Java Bean that represents question about several persons. This entity may be
 * inserted into the data structures and DB dynamically. It must be unique both
 * in {@code id} and {@code description} fields. No question is connected with
 * particular item only but all of them at once.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name="description")
    private String description;

    @Override
    public String toString() {
        return new StringBuilder("Question [id=").append(getId())
                .append(", description=").append(getDescription()).append("]")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;

        return getId() == question.getId();
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
