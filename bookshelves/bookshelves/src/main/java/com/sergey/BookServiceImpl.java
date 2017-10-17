package com.sergey;

import javax.transaction.Transactional;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> listBook() {
        return this.bookDao.listBook();
    }

    @Override
    @Transactional
    public void readAlready(int id) {
        this.bookDao.readAlready(id);
    }

    @Override
    @Transactional

    public List<Book> listBook(int begin, int num) {
        return bookDao.listBook(begin,num);
    }

    @Override
    @Transactional
    public List<Book> searchBook(Book book) {
        return bookDao.searchBook(book);
    }
}
