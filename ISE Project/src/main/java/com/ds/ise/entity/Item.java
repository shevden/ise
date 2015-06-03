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

	@Column(name = "genre")
	private String genre;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "developer")
	private String developer;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "description")
	private String description;

	@Column(name = "cover_path")
	private String coverPath;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
}
