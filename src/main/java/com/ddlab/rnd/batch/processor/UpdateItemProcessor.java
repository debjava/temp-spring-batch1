package com.ddlab.rnd.batch.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.ddlab.rnd.entity.Person;

public class UpdateItemProcessor implements ItemProcessor<Person, Object>{

	@Override
	public Person process(Person item) throws Exception {
		System.out.println("------------ Inside Item Processor ----------");
//		List<Person> pList = new ArrayList<>();
		System.out.println("Processed Item: "+item);
//		pList.add(item);
//		return pList;
		
		return item;
	}

}
