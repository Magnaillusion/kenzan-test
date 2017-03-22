package org.mags.user.api;

import static spark.Spark.*;

import java.io.IOException;
import java.io.InputStream;

import org.mags.user.api.customer.CustomerController;
import org.mags.user.api.db.DatabaseHelper;
import org.mags.user.api.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.utils.IOUtils;

public class App 
{
	Logger logger = LoggerFactory.getLogger(App.class);
	
	public App(){
		port(getHerokuAssignedPort());
		
		new DatabaseHelper();
		
		staticFileLocation("/public");
		
		//Filters		
		enableCORS("*", "*", "*");
		
		//Handle CRUD here
		get(Path.Web.CUSTOMERS, (req, res) -> {return CustomerController.handleGetAllCustomers(req, res);});
		get(Path.Web.GET_CUSTOMER, (req, res) -> {return CustomerController.handleGetCustomer(req, res);});
		post(Path.Web.CREATE_CUSTOMER, (req, res) -> {return CustomerController.handleCreateCustomer(req, res);});
		post(Path.Web.UPDATE_CUSTOMER, (req, res) -> {return CustomerController.handleUpdateCustomer(req, res);});
		delete(Path.Web.DELETE_CUSTOMER, (req, res) -> {return CustomerController.handleDeleteCustomer(req, res);});
		
		//Main Page
		get(Path.Web.HOME, (req, res) ->{
			try (InputStream stream = getClass().getResourceAsStream("/public/index.html")) {
	            return IOUtils.toString(stream);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
		});
	}
	
    public static void main( String[] args )
    {
    	new App();
    }
    
    private static void enableCORS(final String origin, final String methods, final String headers) {
    	options("/*", (req, res) ->{
			String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
		    if (accessControlRequestHeaders != null) {
		        res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
		    }
		 
		    String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
		    if(accessControlRequestMethod != null){
		    res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		    }
		 
		    return "OK";
		});
    	
    	before((request, response) ->{
        	response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            
            response.type("application/json");
        });
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
