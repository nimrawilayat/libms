package com.hurontg.libms.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hurontg.libms.domain.Book;

@Repository
@Qualifier("bookDaoJdbcTemplateImpl")
public class BookDaoJdbcTemplateImpl implements BookDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SQL_SELECT_ALL_BOOKS = "select id, title, author, version from book";

	@Override
	public List<Book> getBooks() {
		List<Book> books = jdbcTemplate.query(SQL_SELECT_ALL_BOOKS, new BookRowMapper());
		return books;
	}

	private static final String SQL_INSERT_BOOK = "insert into book (title, author) values (?, ?)";

	@Override
	public void addBook(Book book) {
		jdbcTemplate.update(SQL_INSERT_BOOK, book.getTitle(), book.getAuthor());
	}

	private static final String SQL_UPDATE_BOOK = "update book set title = ?, author = ?, version = ? where id = ? and version = ?";

	@Override
	public void updateBook(Book book) {
		int rowsUpdated = jdbcTemplate.update(SQL_UPDATE_BOOK, book.getTitle(), book.getAuthor(), book.getVersion()+1, book.getId(), book.getVersion());
		if (rowsUpdated == 0) {
			throw new OptimisticLockingFailureException("An attempt was detected to update row with stale data");			
		}
	}

	private static final String SQL_DELETE_BOOK = "delete from book where id = ?";

	@Override
	public void deleteBook(Long id) {
		jdbcTemplate.update(SQL_DELETE_BOOK, id);
	}

	private static final String SQL_SELECT_BOOK = "select id, title, author, version from book where id = ?";

	@Override
	public Book getBookById(Long id) {
		Book book = jdbcTemplate.queryForObject(SQL_SELECT_BOOK, new BookRowMapper(), id);
		return book;
	}

	class BookRowMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setId(rs.getLong("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setVersion(rs.getLong("version"));
			return book;
		}
	}
}
