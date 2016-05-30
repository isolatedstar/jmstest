package com.sishuok.mongotest;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.util.JSON;

public class MyMongoTest {
	public static void main(String[] args) throws Exception {
		MongoClientOptions mcs = MongoClientOptions.builder().connectionsPerHost(6).connectTimeout(1000).build();
		
		MongoClient mongo = new MongoClient("192.168.1.106:20010",mcs);
		
		System.out.println("ConnectionsPerHost=="+mongo.getMongoClientOptions().getConnectionsPerHost());
		
		
		DB db = mongo.getDB("mydb");
		
		DBCollection users = db.getCollection("users");
		
//		BasicDBObject d1 = new BasicDBObject();
//		d1.put("userId","myU1");
//		d1.append("userId", "myU2").append("name", "updatename222");
		
//		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().add("userId", "u3").add("age", 123);
	
//		String json = "{'userId':'t234','age':234}";
//		DBObject d1 = (DBObject)JSON.parse(json);
		
//		users.insert(d1);
//		users.insert(builder.get());
		
//		users.remove(new BasicDBObject());
		
//		users.update(new BasicDBObject("userId", "myU2"),d1);
//		users.update(new BasicDBObject("userId", "myU2"),
//				new BasicDBObject("$set",
//							new BasicDBObject("name","newName")
//						)
//				);
		
//		DBObject d =  users.findOne();
//		System.out.println("d=="+d);
		
		// {userId:"ttuu" , age:{$gte:100,$lt:200}  }
		
		DBObject con = new BasicDBObject(
				"userId","ttuu").append(
				"age", new BasicDBObject("$gt",120).append("$lt", 210)				
				);
		
		DBObject show = new BasicDBObject("userId",1).append("age",1).append("_id", 0);
				
		DBCursor c = users.find(con,show).limit(2);
		while(c.hasNext()){
			DBObject d = c.next();
			System.out.println("d==="+d);
		}
		
		
		mongo.close();
		
	}
}
