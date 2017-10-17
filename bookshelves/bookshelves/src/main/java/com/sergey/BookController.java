package com.sergey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private BookService bookService;
    private int num = 10;
    private static int count;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBook(Model model) {
        List<Book> listBook = bookService.listBook();
        List<Book> pageBooks = bookService.listBook(0, num);
        count = getPageNumber(listBook.size(), num);
        model.addAttribute("book", new Book());
        model.addAttribute("count", count);
        model.addAttribute("pageBooks", pageBooks);
        model.addAttribute("listBooks", listBook);
        return "books";
    }

    @RequestMapping(value = "/bookspage", method = RequestMethod.GET)
    public String getBooks(@RequestParam(value = "numpage", required = true) Integer numpage, Model model) {
        List<Book> listBook = bookService.listBook();
        int offset = numpage * num - num;
        List<Book> pageBooks = bookService.listBook(offset, num);
        count = getPageNumber(listBook.size(), num);


        model.addAttribute("thisNumber", numpage); //номер текущей страницы
        model.addAttribute("book", new Book()); // книга для поля CREAT BOOK
        model.addAttribute("count", count); // общее количество страниц
        model.addAttribute("pageBooks", pageBooks); // список книг на отображаемой странице
        model.addAttribute("listBooks", listBook); // список всех книг
        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
            List<Book> listBook = bookService.listBook();
            int pageNumber;

            pageNumber = getPageNumber(listBook.size(), num);

            return "redirect:/bookspage?numpage=" + pageNumber;
        } else {
            List<Book> listBook = bookService.listBook();
            int count = 1;
            int pageNumber = 1;

            for (Book b : listBook) {
                if (b.getId() == book.getId()) {
                    pageNumber=getPageNumber(count, num);
                    break;
                }
                count++;
            }
            bookService.updateBook(book);
            return "redirect:/bookspage?numpage=" + pageNumber;
        }
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        List<Book> listBook = bookService.listBook();
        int count = 1;
        int pageNumber = 1;

        for (Book book : listBook) {
            if (book.getId() == id) {
                if (count < num) {
                    pageNumber = 1;

                } else if (count == listBook.size() && count % num == 1) {
                    pageNumber = count / num;
                } else if (count % num > 0) {
                    pageNumber = count / num + 1;
                } else if (count == listBook.size() && count % num == 1) {
                    pageNumber = count / num;
                } else {
                    pageNumber = count / num;
                }
            }
            count++;
        }

        bookService.removeBook(id);
        return "redirect:/bookspage?numpage=" + pageNumber;
    }

    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        List<Book> listBook = bookService.listBook();
        int numpage=1;
        count = 1;
        for (Book b : listBook) {
            if (b.getId() ==id) {
                numpage=getPageNumber(count, num);
                System.out.println("Найденная книга: "+b);
                break;
            }
            count++;
        }
        System.out.println("Номер страницы: "+numpage);

        int offset = numpage * num - num;


        List<Book> pageBooks = bookService.listBook(offset, num);
        count = getPageNumber(listBook.size(), num);
        System.out.println("Общее число страниц:  "+count);



        model.addAttribute("thisNumber", numpage);
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("count", count);
        model.addAttribute("pageBooks", pageBooks);
        model.addAttribute("listBooks", listBook);
        return "books";

      /*  model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBook());
        return "books";*/
    }

    @RequestMapping("/bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "bookdata";
    }

    @RequestMapping("/readAlready/{id}")
    public String readAlready(@PathVariable("id") int id) {
        List<Book> listBook = bookService.listBook();
        int count = 1;
        int pageNumber = 1;

        for (Book b : listBook) {
            if (b.getId() == id) {
                pageNumber=getPageNumber(count, num);
                break;
            }
            count++;
        }
        bookService.readAlready(id);
        return "redirect:/bookspage?numpage=" + pageNumber;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String foundBook(@ModelAttribute("book") Book book, Model model) {
        List<Book> listBook ;
        if (book.getId()==0) {
             listBook = bookService.listBook();
        }
        else {listBook = bookService.searchBook(book);

        }

        model.addAttribute("bookFilter", book);

        model.addAttribute("listBooks", listBook);
        return "search";
    }

    @RequestMapping(value = "/searching", method = RequestMethod.POST)
    public String listFilter(@ModelAttribute("book") Book book, Model model) {
        System.out.println(book);

        List<Book> listBook = bookService.searchBook(book);

        model.addAttribute("bookFilter", book);
        model.addAttribute("listBooks", listBook);
        return "search";
    }


    private int getPageNumber(int listSize, int num) {
        int pageNumber;
        if (listSize < num) {
            pageNumber = 1;
        } else if (listSize % num > 0) {
            pageNumber = listSize / num + 1;
        } else {
            pageNumber = listSize / num;
        }
        return pageNumber;
    }





}
