//package com.teamalpha.datastore;
//
//import com.google.appengine.api.datastore.*;
//
//import com.teamalpha.model.FenceModel;
//
//public class Fence extends EntityWrapper {
//
//        public Fence() {
//                super();
//        }
//        
//        public Fence(Entity entity) {
//                super(entity);
//        }
//        
//        public GeoPt getPointOne() {
//                return (GeoPt) super.get("Point One");
//        }
//        
//        public void setPointOne(GeoPt value) {
//                super.set("Point One", value);
//        }
//        
//        public GeoPt getPointTwo() {
//                return (GeoPt) super.get("Point Two");
//        }
//        
//        public void setPointTwo(GeoPt value) {
//                super.set("Point Two", value);
//        }
//        
//        //Need to implement patient Id
//        public String getID() {
//                return super.keyString();
//        }
//        
//        public GeoPt getPointThree() {
//                return (GeoPt) super.get("Point Three");
//        }
//        
//        public void setPointThree(GeoPt value) {
//                super.set("Point Three", value);
//        }
//        
//        public GeoPt getPointFour() {
//                return (GeoPt) super.get("Point Four");
//        }
//        
//        public void setPointFour(GeoPt value) {
//                super.set("Point Four", value);
//        }
//        
//        public FenceModel getModel() {
//                return new FenceModel(this);
//        }
//        
//        public void copyModel(FenceModel model) {
//                if (model.getPointOne() != null) {
//                        this.setPointOne(model.getPointOne());
//                }
//                if (model.getPointTwo() != null) {
//                        this.setPointTwo(model.getPointTwo());
//                }
//                if (model.getPointThree() != null) {
//                        this.setPointThree(model.getPointThree());
//                }
//                if (model.getPointFour() != null) {
//                        this.setPointFour(model.getPointFour());
//                }
//        }
//        
//}
