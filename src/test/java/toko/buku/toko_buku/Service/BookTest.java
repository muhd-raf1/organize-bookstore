package toko.buku.toko_buku.Service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;
import toko.buku.toko_buku.Service.Book.BookService;
import toko.buku.toko_buku.Service.Book.BookServiceImpl;

public class BookTest {

    private BooksRepository booksRepository = new BooksRepositoryImpl();
    private BookService bookService = new BookServiceImpl(booksRepository);

    @Test
    void testAddFail() {

        BooksEntity book = new BooksEntity();
        boolean test = bookService.add(book);

        Assertions.assertFalse(test);

    }

    @Test
    void testAddSuccess() {

        BooksEntity book = new BooksEntity();
        book.setAuthor("Tere Liye");
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good");
        book.setId("A0002");
        book.setIdCategori(new CategoriesEntity("Fantasy"));
        book.setPages(193);
        book.setPublisher("Cahaya Sdn. Bhd");
        book.setTitle("Hujan");
        book.setYearOfPublish(LocalDateTime.of(2018, 5, 27, 0, 0));
        book.setUpdatedAt(LocalDateTime.now());

        boolean test = bookService.add(book);

        Assertions.assertTrue(test);
    }

    @Test
    void testAddBookNotNullButFail() {

        BooksEntity book = new BooksEntity();
        book.setAuthor("");
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good");
        book.setId("");
        book.setIdCategori(new CategoriesEntity(""));
        book.setPages(193);
        book.setPublisher("Cahaya Sdn. Bhd");
        book.setTitle("");
        book.setYearOfPublish(LocalDateTime.of(2018, 5, 27, 0, 0));
        book.setUpdatedAt(LocalDateTime.now());

        boolean test = bookService.add(book);

        Assertions.assertFalse(test);
    }
}
