package com.ddlab.rnd.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemReader")
public class InsertItemReader implements ItemReader<Person> {

private ItemReader<Person> delegate;
	
	@Override
    public Person read() throws Exception {
		System.out.println("------------ Inside InsertItemReader in Insert Step ----------");
        if (delegate == null) {
            delegate = new IteratorItemReader<>(persons());
        }
        return delegate.read();
    }
	
	private List<Person> persons() {
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			personList.add(new Person(i, "Name-" + i, null));
		}
		return personList;
    }

}
