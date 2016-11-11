package com.hurontg.libms.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.hurontg.libms.domain.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	JdbcOperations jdbcOperations;
	
	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(1L, "The Temple Tiger", "Jim Corbett"));
		books.add(new Book(2L, "Jungle Lore", "Jim Corbett"));
		return books;
	}

	@Override
	public List<String> getProducts() {
		List<String> products = new ArrayList<>();

		List<Map<String, Object>> rows = jdbcOperations.queryForList("select description from nextbank.product");
		for (Map row : rows) {
			String product = (String)(row.get("description"));
			products.add(product);
		}
		
		return products;
	}
	
}
