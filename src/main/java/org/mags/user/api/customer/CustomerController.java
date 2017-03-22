package org.mags.user.api.customer;

import org.mags.user.api.db.DatabaseHelper;
import org.mags.user.api.util.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
	protected static DatabaseHelper dbHelper = new DatabaseHelper();
	protected static Datastore ds;
	
	private static String REJECT_PATTERN = "[^a-zA-Z0-9:\",{}@_.\\- ]";
	
	public CustomerController(){}
	
	public static String handleCreateCustomer(Request req, Response res) {
		String data = Jsoup.parse(req.body()).text().replaceAll(REJECT_PATTERN, "");
		Customer customer = null;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			customer = objectMapper.readValue(data, Customer.class);
			
			ds = dbHelper.getDatastore();
			
			ds.save(customer);
			res.status(Path.Reply.CREATED);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			res.status(Path.Reply.INTERNAL_SERVER_ERROR);
		}
		
		return customer != null ? customer.toJson() : "Customer not created";
	}
	
	public static String handleGetCustomer(Request req, Response res) {
		res.type("application/json");
		Customer customer = null;
		
		try{
		    ds = dbHelper.getDatastore();
		    customer = ds.createQuery(Customer.class)
		    		     .field("_id").equal(new ObjectId(req.params(":id")))
		    		     .get();
		    res.status(Path.Reply.OK);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			res.status(Path.Reply.INTERNAL_SERVER_ERROR);
		}
		
		return (customer != null) ? customer.toJson() : "Customer not found";
	}
	
	public static String handleGetAllCustomers(Request req, Response res) {
		res.type("application/json");
		List<Customer> customers = new ArrayList<Customer>();
		StringBuilder reply = new StringBuilder();
		
		try {
			ds = dbHelper.getDatastore();
			customers = ds.createQuery(Customer.class).asList();
			
			res.status(Path.Reply.OK);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			res.status(Path.Reply.INTERNAL_SERVER_ERROR);
		}
		
		reply.append("[");
		
		for(int i = 0; i < customers.size(); ++i) {
			if (i != 0) {
				reply.append(",");
			}
			
			reply.append(customers.get(i).toJson());
		}
		
		reply.append("]");
		
		return reply.toString();
	}
	
	public static String handleUpdateCustomer(Request req, Response res) {
		Customer original = null;
		
		try {
			ds = dbHelper.getDatastore();
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			String data = Jsoup.parse(req.body()).text().replaceAll(REJECT_PATTERN, "");
			
			Customer c = objectMapper.readValue(data, Customer.class);
			
			original = ds.createQuery(Customer.class)
	    		     .field("_id").equal(new ObjectId(req.params(":id")))
	    		     .get();
			
			
			original.setName(c.getName());
			original.setEmail(c.getEmail());
			original.setTelephone(c.getTelephone());
			original.setAddress(c.getAddress());
			
			ds.save(original);
			res.status(Path.Reply.OK);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			res.status(Path.Reply.INTERNAL_SERVER_ERROR);
		}
		
		return original != null ? original.toJson() : null;
	}
	
	public static String handleDeleteCustomer(Request req, Response res) {
		Customer customer = null;
		
		try {
			ds = dbHelper.getDatastore();
			customer = ds.createQuery(Customer.class)
	    		     .field("_id").equal(new ObjectId(req.params(":id")))
	    		     .get();
			
			if(customer == null) {
				res.status(Path.Reply.NOT_FOUND);
			}
			
			else {
				ds.delete(customer);
				res.status(Path.Reply.OK);
			}
		}
		
		catch(Exception e) {
			res.status(Path.Reply.INTERNAL_SERVER_ERROR);
		}

		return "200";
	}
}
