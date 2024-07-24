package com.ddlab.rnd.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("updateItemReader1")
public class UpdateItemReader implements ItemReader<Person> {
	
	List<Person> personList = new ArrayList<>();
	
	public UpdateItemReader() {
		for (int i = 0; i < 30; i++) {
			personList.add(new Person(i, "Name-" + i, "Data-" + i));
		}
	}

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(!personList.isEmpty()) {
			return personList.remove(0);
		}
		return null;
	}

}
