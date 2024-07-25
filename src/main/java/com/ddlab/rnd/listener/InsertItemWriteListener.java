package com.ddlab.rnd.listener;

import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemWriteListener")
public class InsertItemWriteListener /* implements ItemWriteListener<Object> */ {

	@BeforeWrite
	public void beforeWrite(Chunk<? extends Person> items) {
		items.forEach( value -> {
			System.out.println("In InsertItemWriteListener data before write value--->"+value);
		});
		
	}
	
	@AfterWrite
	public void afterWrite(Chunk<? extends Object> items) {
		items.forEach( value -> {
			System.out.println("In InsertItemWriteListener data after write value--->"+value);
		});
	}
	
	@OnWriteError
	public void onWriteError(Exception exception, Chunk<? extends Object> items) {
		
	}
}
