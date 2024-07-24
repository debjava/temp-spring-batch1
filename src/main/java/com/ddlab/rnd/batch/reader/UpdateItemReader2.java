package com.ddlab.rnd.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("updateItemReader2")
public class UpdateItemReader2 implements ItemReader<Person> {

	private ItemReader<Person> delegate;
	
	@Override
    public Person read() throws Exception {
        if (delegate == null) {
            delegate = new IteratorItemReader<>(persons());
        }
        return delegate.read();
    }
	
	private List<Person> persons() {
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			personList.add(new Person(i, "Name-" + i, "Data-" + i));
		}
		return personList;
    }


}
