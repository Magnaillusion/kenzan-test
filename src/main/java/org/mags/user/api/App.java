package org.mags.user.api;

import static spark.Spark.*;

import org.mags.user.api.customer.CustomerController;
import org.mags.user.api.db.DatabaseHelper;
import org.mags.user.api.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Session;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class App 
{
	Logger logger = LoggerFactory.getLogger(App.class);
	
	public App(){
		port(8080);
		
		new DatabaseHelper();
		
		//Handle CRUD here
		get(Path.Web.GET_CUSTOMER, (req, res) -> {return CustomerController.handleGetCustomer(req, res);});
		post(Path.Web.CREATE_CUSTOMER, (req, res) -> {return CustomerController.handleCreateCustomer(req, res);});
		post(Path.Web.UPDATE_CUSTOMER, (req, res) -> {return CustomerController.handleUpdateCustomer(req, res);});
		delete(Path.Web.DELETE_CUSTOMER, (req, res) -> {return CustomerController.handleDeleteCustomer(req, res);});
	}
	
    public static void main( String[] args )
    {
    	new App();
    }
}
