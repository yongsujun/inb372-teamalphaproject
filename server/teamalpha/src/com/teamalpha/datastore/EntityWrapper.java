package com.teamalpha.datastore;

import java.util.ArrayList;

import com.google.appengine.api.datastore.*;

public abstract class EntityWrapper {
	
	private Entity entity;
	   
    public EntityWrapper() {
            this.entity = null;
    }
   
    public EntityWrapper(Entity entity) {
            this.entity = entity;
    }
   
    protected void set(String property, String value) {
            this.entity.setProperty(property, value);
    }
    
    protected void set(String property, long value) {
    	this.entity.setProperty(property, value);
    }
    
    protected void set(String property, GeoPt value) {
            this.entity.setProperty(property, value);
    }
    
    protected void set(String property, ArrayList<String> list) {
    	this.entity.setProperty(property, list);
    }
   
    protected Object get(String property) {
            return this.entity.getProperty(property);
    }
   
    public void commit() {
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(this.entity);
    }
   
    public String keyString() {
    	return KeyFactory.keyToString(this.entity.getKey());
    }
   
    
   
}


