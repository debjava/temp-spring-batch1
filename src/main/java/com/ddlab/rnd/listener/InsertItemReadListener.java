package com.ddlab.rnd.listener;

import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemReadListener")
public class InsertItemReadListener  {

	@BeforeRead
	public void beforeRead() {
		System.out.println("InsertItemReadListener Reading a new Person Record : ");
	}

	@AfterRead
	public void afterRead(Person input) {
		System.out.println("InsertItemReadListener New Person record read : " + input);
	}

	@OnReadError
	public void onReadError(Exception e) {
		System.out.println("InsertItemReadListener Error in reading the person record : " + e);
	}

}
