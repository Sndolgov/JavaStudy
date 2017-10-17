package com.sergey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        if (!book.getAuthor().equals("") && !book.getDescription().equals("") && !book.getAuthor().equals("") && !book.getIsbn().equals("") && book.getPrintYear() != 0) {
            session.persist(book);
            logger.info("Book successfully saved. Book details:" + book);
        }
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        book.setReadAlready(false);
        session.update(book);
        logger.info("Book successfully update. Book details:" + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null)
            session.delete(book);
        logger.info("Book successfully remove. Book details:" + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        logger.info("Book successfully loaded. Book details:" + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBook() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> list = session.createQuery("from Book").list();
        for (Book book : list) {
            logger.info("Book list: " + list);
        }
        return list;
    }

    @Override
    public void readAlready(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null) {
            book.setReadAlready(true);
            session.update(book);
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBook(int begin, int num) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from Book").list();
        List<Book> booksOnPage = new ArrayList<Book>();
        if (begin >= books.size()) {
            return books;
        } else if (begin < books.size() && books.size() < (begin + num)) {
            for (int i = begin; i < books.size(); i++) {
                booksOnPage.add(books.get(i));
            }
            return booksOnPage;
        } else {
            for (int i = begin; i < begin + num; i++) {
                booksOnPage.add(books.get(i));
            }
            return booksOnPage;
        }
    }

    @Override
    public List<Book> searchBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> list = session.createQuery("from Book").list();

        System.out.println("1");
        if (book.getId() != 0) {
            for (int i = 0; i < list.size(); ) {
                if (list.get(i).getId() != book.getId())
                    list.remove(i);
                else i++;
            }
        }

        System.out.println("2");
        if (book.getTitle()!=null && !book.getTitle().equals("") ) {
            for (int i = 0; i < list.size(); ) {
                if (!list.get(i).getTitle().toLowerCase().contains(book.getTitle().toLowerCase()))
                    list.remove(i);
                else i++;
            }
        }

        System.out.println("3");
        if (book.getDescription()!=null && !book.getDescription().equals("")) {
            for (int i = 0; i < list.size(); ) {
                if (!list.get(i).getDescription().toLowerCase().contains(book.getDescription().toLowerCase()))
                    list.remove(i);
                else i++;
            }
        }

        System.out.println("4");
        if (book.getAuthor()!=null && !book.getAuthor().equals("")) {
            for (int i = 0; i < list.size(); ) {
                if (!list.get(i).getAuthor().toLowerCase().contains(book.getAuthor().toLowerCase()))
                    list.remove(i);
                else i++;
            }
        }

        System.out.println("5");
        if (book.getIsbn()!=null && !book.getIsbn().equals("")) {
            for (int i = 0; i < list.size(); ) {
                if (!list.get(i).getIsbn().toLowerCase().contains(book.getIsbn().toLowerCase()))
                    list.remove(i);
                else i++;
            }
        }

        System.out.println("6");
        if (book.getPrintYear() != 0) {
            for (int i = 0; i < list.size(); ) {
                if (list.get(i).getPrintYear() != book.getPrintYear())
                    list.remove(i);
                else i++;
            }
        }
        if (book.getAuthor()==null){
            for (int i = 0; i < list.size(); ) {
                if (list.get(i).isReadAlready() != book.isReadAlready())
                    list.remove(i);
                else i++;
            }
        }


        return list;
    }
}
