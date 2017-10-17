package com.sergey;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> listBook();

    void readAlready(int id);

    List<Book> listBook(int begin, int num);

    List<Book> searchBook(Book book);
}
