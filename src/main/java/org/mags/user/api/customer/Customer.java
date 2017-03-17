package org.mags.user.api.customer;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("customers")
public class Customer {
	@Id
	private ObjectId id;
	
	private String name = "";
	private String email = "";
	private String telephone = "";
	private Address address = new Address();
	
	public Customer(){}
	
	public Customer(String name) {
		this.name = name;
	}
	
	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Customer(String name, String email, String telephone) {
		this.name = name;
		this.email = email;
		this.telephone = telephone;
	}
	
	public Customer(String name, String email, String telephone, Address address) {
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
	}
	
	public ObjectId getId() {
		return (id != null) ? id : null;
	}
	
	public void setId(String id) {
		if(id != null && !id.isEmpty()) {
			this.id = new ObjectId(id);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "id = " + id + "\n" +
	           "name = " + name + "\n" +
	           "email = " + email + "\n" +
	           "telephone = " + telephone + "\n" +
	           "address = " + address.toString() + "\n";
	}
	
	public String toJson() {
		return "{" +
	
	               "\"id\": " + "\"" + id + "\", " +
	               "\"name\": " + "\"" + name + "\", " +
	               "\"email\": " + "\"" + email + "\", " +
	               "\"telephone\": " + "\"" + telephone + "\", " +
	               "\"address\": " + address.toJson() + 
			   "}";
	}
}
