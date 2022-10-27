package com.infinite.LibraryProjectHib;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LibraryDAO {
	SessionFactory sessionFactory;
	
	public int loginCheck(String userName, String password) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(LibUsers.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));
		List<LibUsers> listUsers = criteria.list();
		return listUsers.size();
	}
	
	public List<Books> searchBooks(String searchtype, String searchvalue) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(Books.class);

		if (searchtype.equals("id")) {
			int id =Integer.parseInt(searchvalue);
			cr.add(Restrictions.eq("id", id));
		} 
		if (searchtype.equalsIgnoreCase("dept")) {
			cr.add(Restrictions.eq("dept", searchvalue));
		}

		if (searchtype.equalsIgnoreCase("bookname")) {
			cr.add(Restrictions.eq("name", searchvalue));
		}

		if (searchtype.equalsIgnoreCase("authorname")) {
			cr.add(Restrictions.eq("author", searchvalue));
		}

		List<Books> lst = cr.list();
		return lst;
	}
	
	public Books searchById(int id) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(Books.class);
		cr.add(Restrictions.eq("id", id));
		List<Books> booksList = cr.list();
		return booksList.get(0);
	}
	
	public String issueBook(TranBook tranBook) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		if (issueOrNot(tranBook.getBookId())==1) {
			return "Book with id " +tranBook.getBookId() + " Already issued...";
		} 
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new Date(date.getTime());
		tranBook.setFromDate(sqlDate);
		Transaction tran = session.beginTransaction();
		session.save(tranBook);
		tran.commit();
		session.close();
		session = sessionFactory.openSession();
		Books book = searchById(tranBook.getBookId());
		book.setTotalBooks(book.getTotalBooks()-1);
		tran=session.beginTransaction();
		session.saveOrUpdate(book);
		tran.commit();
		return "Book with Id " +tranBook.getBookId() + " Issued Successfully...";
	}
	
	public int issueOrNot(int bookId) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(TranBook.class);
		cr.add(Restrictions.eq("bookId", bookId));
		return cr.list().size();
	}
	
	public TranBook search(int bookId, String user) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(TranBook.class);
		cr.add(Restrictions.eq("bookId", bookId));
		cr.add(Restrictions.eq("userName", user));
		List<TranBook> list = cr.list();
		return list.get(0);
	}
	
	public List<TranBook> issueBooks(String user){
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(TranBook.class);
		List<TranBook> lst = cr.list();
		return lst;
	}
	
	public String returnBookDAO(int bookId, String user) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		
		TranBook tranBook = search(bookId, user);
		Date issueDate = tranBook.getFromDate();
		
		ReturnBook returnBook = new ReturnBook();
		
		returnBook.setBookId(bookId);
		returnBook.setUsername(user);
		returnBook.setFromDate(issueDate);
		
		Transaction tran = session.beginTransaction();
		session.save(returnBook);
		tran.commit();
		session.close();
		
		session = sessionFactory.openSession();
		tran = session.beginTransaction();
		session.delete(tranBook);
		tran.commit();
		session.close();
		
		Books books = searchById(bookId);
		books.setTotalBooks(books.getTotalBooks() + 1);
		
		session = sessionFactory.openSession();
		tran = session.beginTransaction();
		session.update(books);
		tran.commit();
		session.close();
		
		return "Book with Id "+bookId + " Returned Successfully...";
	}
	
	public List<ReturnBook> history(String user) {		
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession(); 
		Criteria cr = session.createCriteria(ReturnBook.class);
		cr.add(Restrictions.eq("username", user));
		List<ReturnBook> lst = cr.list();
		return lst;
	}
}
