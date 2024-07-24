package com.ddlab.rnd.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.ddlab.rnd.entity.Person;

public class UpdateItemWriter implements ItemWriter<Person> {

	@Override
	public void write(Chunk<? extends Person> chunk) throws Exception {
		System.out.println("--------------- WRITING ITEMS ------------------------");
		System.out.println("Chunk Items: "+chunk.getItems());
		chunk.getItems().stream().forEach( value -> System.out.println("Value to write--->"+value));
		System.out.println("--------------- WRITING ITEMS ------------------------");
	}

}
