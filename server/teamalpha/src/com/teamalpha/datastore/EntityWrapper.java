package com.teamalpha.datastore;

import com.google.appengine.api.datastore.*;

public abstract class EntityWrapper {
	
	private Entity entity;
	   
    public EntityWrapper() {
            this.entity = null;
    }
   
    public EntityWrapper(Entity entity) {
            this.entity = entity;
    }
   
    public void set(String property, String value) {
            this.entity.setProperty(property, value);
    }
   
    public Object get(String property) {
            return this.entity.getProperty(property);
    }
   
    public void commit() {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(this.entity);
    }
   
    public String keyString() {
            return KeyFactory.keyToString(this.entity.getKey());
    }
   
    public abstract String xml();
   
}


