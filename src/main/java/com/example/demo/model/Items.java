package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Items")
@EntityListeners(AuditingEntityListener.class)
public class Items {
	
//	 id, name, description, price
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)  
	@Column(name="Id")
	private Long id;
	
	@NotBlank
	@Column(name="Name")
	private String itemName;
	
	@NotBlank
	@Column(name="Description")
	private String itemDescription;
	
	//@NotBlank
	@Column(name="Price")
	private int itemPrice;
	
	
	public Items() {
		super();
	}


	public Items(Long id, String itemName, String itemDescription, int itemPrice) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
	
	
	
	
	

	 

}
