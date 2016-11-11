package com.hurontg.libms.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
			String product = (String) (row.get("description"));
			products.add(product);
		}

		return products;
	}

	private static final String SQL_INSERT_BOOK = "insert into book (title, author) values (?, ?)";
	@Autowired
	private DataSource dataSource;

	public void addBook(Book book) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_BOOK);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Error inserting Book", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static final String SQL_UPDATE_BOOK = "update book set author = ?, title = ? where id = ?";

	public void saveBook(Book book) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_BOOK);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setLong(3, book.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating Book", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static final String SQL_SELECT_BOOK = "select id, title, author from book where id = ?";

	public Book findOne(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BOOK);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			Book book = null;
			if (rs.next()) {
				book = new Book();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
			}
			return book;
		} catch (SQLException e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}
	
	private static final String SQL_SELECT_ALL_BOOKS = "select id, title, author from book";

	public List<Book> findAllBooks() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Book> books = new ArrayList<>();
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL_BOOKS);
			rs = stmt.executeQuery();			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				
				books.add(book);
			}			
		} catch (SQLException e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return books;
	}

}


