package com.ecole.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String label;
	private String description;
	@ManyToOne
    @JoinColumn(name ="id_category")
	private Category category;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", label=" + label + ", description=" + description + ", category=" + category
				+ "]";
	}
	

    

}