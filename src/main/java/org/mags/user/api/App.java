package org.mags.user.api;

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args )
    {
    	get("/hello", (req, res) -> {
    		res.status(200);
    		res.type("application/json");
    		return "{'message': 'Hello " + req.queryParams("name") + "'}";
    	});
    }
}
