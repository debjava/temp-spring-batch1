package com.ddlab.rnd.listener;

import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemProcessListener")
public class InsertItemProcessListener {

	@BeforeProcess
	public void beforeProcess(Person item) {
		System.out.println("InsertItemProcessListener Before Processing person item: "+item);
	}
	
	@AfterProcess
	public void afterProcess(Person item, Object result) {
		System.out.println("InsertItemProcessListener After Processing person item: "+item);
	}
	
	@OnProcessError
	public void onProcessError(Person item, Exception e) {
		System.out.println("InsertItemProcessListener onProcessError for Processing person item: "+item);
	}
}
