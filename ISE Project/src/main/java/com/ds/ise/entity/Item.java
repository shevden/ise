package com.ds.ise.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Java Bean that represents items that may be searched for. This entity may be
 * inserted into the data structures and DB dynamically. It must be unique in
 * {@code id} field. No item is connected with particular question only but
 * all of them at once.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "requests")
	private long requests;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", requests="
				+ requests + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public long getRequests() {
		return requests;
	}

	public void setRequests(long requests) {
		this.requests = requests;
	}

	public void incrementRequests() {
		++requests;
	}

}
