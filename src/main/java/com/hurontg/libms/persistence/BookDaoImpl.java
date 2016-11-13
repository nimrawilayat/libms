package com.hurontg.libms.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hurontg.libms.domain.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private DataSource dataSource;

	private static final String SQL_SELECT_ALL_BOOKS = "select id, title, author from book";

	@Override
	public List<Book> getBooks() {
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
			e.printStackTrace();
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

	private static final String SQL_INSERT_BOOK = "insert into book (title, author) values (?, ?)";

	@Override
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
	
//
//	private static final String SQL_UPDATE_BOOK = "update book set author = ?, title = ? where id = ?";
//
//	public void saveBook(Book book) {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			stmt = conn.prepareStatement(SQL_UPDATE_BOOK);
//			stmt.setString(1, book.getTitle());
//			stmt.setString(2, book.getAuthor());
//			stmt.setLong(3, book.getId());
//			stmt.execute();
//		} catch (SQLException e) {
//			throw new RuntimeException("Error updating Book", e);
//		} finally {
//			try {
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private static final String SQL_SELECT_BOOK = "select id, title, author from book where id = ?";
//
//	public Book findOne(long id) {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dataSource.getConnection();
//			stmt = conn.prepareStatement(SQL_SELECT_BOOK);
//			stmt.setLong(1, id);
//			rs = stmt.executeQuery();
//			Book book = null;
//			if (rs.next()) {
//				book = new Book();
//				book.setId(rs.getLong("id"));
//				book.setTitle(rs.getString("title"));
//				book.setAuthor(rs.getString("author"));
//			}
//			return book;
//		} catch (SQLException e) {
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//				}
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return null;
//	}
	

}


