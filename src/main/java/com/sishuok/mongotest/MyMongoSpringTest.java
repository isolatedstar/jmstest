package com.sishuok.mongotest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

//@Service
public class MyMongoSpringTest {
//	@Autowired
	private MongoTemplate mt = null;
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MyMongoSpringTest t = (MyMongoSpringTest)ctx.getBean("myMongoSpringTest");
		
		final String COL_NAME = "users";
		
		//add
//		UserModel um = new UserModel();
//		um.setAge(1);
//		um.setName("n1");
//		um.setUserId("u1");
//		t.mt.insert(um,COL_NAME);
		
		//update
		//userId=t222 or (age>=100 and age<130)
		Criteria c = 
			new Criteria().andOperator(
				new Criteria("userId").is("t222"),
				new Criteria().andOperator(
						new Criteria("age").gte(100),
						new Criteria("age").lt(130)
				)
			);
		t.mt.updateMulti(new Query(c), Update.update("name", "nn126"), UserModel.class,COL_NAME);
		
		//remove
		//
//		Criteria c = new Criteria("userId").is("t1");		
//		t.mt.remove(new Query(c), COL_NAME);
		
		List<UserModel> list = t.mt.find(new Query(c),UserModel.class,COL_NAME);
		System.out.println("list==="+list);
		
	}
}
