package com.anjaniy.pms.models;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private String category;
	private long price;
	
	public Product(int id, String name, String description, String category, long price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	
	
	public Product(String name, String description, String category, long price) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	

}
