package org.mags.user.api.util;

public class Path {
	public Path(){}
	
	public static class Web {
		public static String HOME = "/";
		
		public static String CUSTOMERS       = HOME + "customers/";
		public static String GET_CUSTOMER    = HOME + "customers/:id";
		public static String DELETE_CUSTOMER = HOME + "customers/:id";  // Uses POST
		public static String UPDATE_CUSTOMER = HOME + "customers/:id";  // Uses POST
		public static String CREATE_CUSTOMER = HOME + "customers/";     // Uses POST
	}
	
	public static class Templates {}
	
	public static class Database {
		public static String LOCAL_DBNAME = "db_customers";
		public static String HOST = "127.0.0.1";
		public static int PORT = 27017;
		
		//Heroku definitions
		//TODO: Place actual definitions
		public static String HEROKU_DB_URI = "";
		public static String HEROKU_DB_NAME = "";
	}
	
	public static class Reply {
		public static int OK = 200;
		public static int CREATED = 201;
		public static int BAD_REQUEST = 400;
		public static int UNAUTHORIZED = 401;
		public static int FORBIDDEN = 403;
		public static int NOT_FOUND = 404;
		public static int INTERNAL_SERVER_ERROR = 500;
	}
}
