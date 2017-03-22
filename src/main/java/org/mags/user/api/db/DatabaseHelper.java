package org.mags.user.api.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mags.user.api.customer.Customer;
import org.mags.user.api.util.Path;

public class DatabaseHelper {
	private static Morphia morphia = new Morphia();
	private static Datastore datastore = null;
	
	public DatabaseHelper() {
		if(!morphia.isMapped(Customer.class)) {
			morphia.map(Customer.class);
			initDatastore();
		}
	}
	
	protected void initDatastore() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		MongoClient mongoClient;
		
		// Connection String for Heroku
		String HEROKU_MLAB_URI = processBuilder.environment().get("MONGODB_URI");
		
		if (HEROKU_MLAB_URI != null && !HEROKU_MLAB_URI.isEmpty()) {
              mongoClient = new MongoClient(new MongoClientURI(HEROKU_MLAB_URI));
              datastore = morphia.createDatastore(mongoClient, Path.Database.HEROKU_DB_NAME);
           } else {
               mongoClient = new MongoClient(Path.Database.HOST, Path.Database.PORT);
               datastore = morphia.createDatastore(mongoClient, Path.Database.LOCAL_DBNAME);
         }
		
		datastore.ensureIndexes();
	}
	
	public Datastore getDatastore() {
		if(datastore == null) {
			initDatastore();
		}
		
		return datastore;
	}
}
