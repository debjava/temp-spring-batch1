package com.ddlab.rnd.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemProcessor")
public class InsertItemProcessor implements ItemProcessor<Object, Object> {

	@Override
	public Person process(Object obj) throws Exception {
		System.out.println("------------ Inside Item Processor in Insert Step ----------");
		Person item = (Person) obj;
//		List<Person> pList = new ArrayList<>();
		System.out.println("Processed Item: "+item);
		System.out.println("After Modification");
		item.setData("Some Data for processing");
		System.out.println("------------ Inside Item Processor in Insert Step ----------");
		return item;
	}

}
