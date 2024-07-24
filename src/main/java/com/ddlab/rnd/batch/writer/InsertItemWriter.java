package com.ddlab.rnd.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("insertItemWriter")
public class InsertItemWriter implements ItemWriter<Object> {
	
	@Override
	public void write(Chunk<? extends Object> chunk) throws Exception {
		System.out.println("--------------- WRITING ITEMS in Insert Step ------------------------");
		System.out.println("Chunk Items: "+chunk.getItems());
		chunk.getItems().stream().forEach( value -> {
			Person p =(Person) value;
			System.out.println("Value to write Person Object--->"+p);
		});
		System.out.println("--------------- WRITING ITEMS in Insert Step ------------------------");
		
	}

//	@Override
//	public void write(Chunk<? extends Person> chunk) throws Exception {
//		System.out.println("--------------- WRITING ITEMS in Insert Step ------------------------");
//		System.out.println("Chunk Items: "+chunk.getItems());
//		chunk.getItems().stream().forEach( value -> System.out.println("Value to write--->"+value));
//		System.out.println("--------------- WRITING ITEMS in Insert Step ------------------------");
//	}
}
