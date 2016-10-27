package com.hurontg.libms.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hurontg.libms.domain.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L, "The Temple Tiger", "Jim Corbett"));
		books.add(new Book(2L, "Jungle Lore", "Jim Corbett"));
		return books;
	}

}
