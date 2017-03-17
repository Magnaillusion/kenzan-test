package org.mags.user.api.customer;

import org.mongodb.morphia.annotations.*;

@Embedded
public class Address {
	private String street = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	
	public Address() {}
	
	public Address(String street) {
		this.street = street;
	}
	
	public Address(String street, String city) {
		this.street = street;
		this.city = city;
	}
	
	public Address(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "street = " + street + "\n" +
	           "city = " + city + "\n" +
			   "state = " + state + "\n" +
	           "zip = " + zip + "\n";
	}
	
	public String toJson() {
		return "{" +
	                "\"street\": " + "\"" + street +"\"" + "," +
	                "\"city\": " + "\"" + city +"\"" + "," +
	                "\"state\": " + "\"" + state +"\"" + "," +
	                "\"zip\": " + "\"" + zip +"\"" +
	           "}";
	}
}
