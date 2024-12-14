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
        book.setId("A0001");
        book.setIdCategori(new CategoriesEntity("Fantasy"));
        book.setPages(233);
        book.setPublisher("Cahaya Sdn. Bhd");
        book.setTitle("Pulang");
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

    @Test
    void testDeleteFail() {
        boolean delete = bookService.delete("       ");
        Assertions.assertFalse(delete);
    }

    @Test
    void testDeleteSuccess() {
        boolean delete = bookService.delete("A0001");
        Assertions.assertTrue(delete);
    }

    @Test
    void testDeleteNotFoundFail() {
        boolean delete = bookService.delete("A0001");
        Assertions.assertFalse(delete);
    }

    @Test
    void testUpdateSuccess() {

        BooksEntity book = new BooksEntity();
        book.setTitle("Test");

        boolean update = bookService.update("A0003", book);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateNotNullButFail() {
        BooksEntity book = new BooksEntity();
        book.setTitle("Test");

        boolean update = bookService.update("A0001", book);
        Assertions.assertFalse(update);
    }

    @Test
    void testUpdateBlankSpaceIdAndNullBookParameterFail() {
        BooksEntity book = new BooksEntity();
        book.setTitle("Test");

        boolean test1 = bookService.update("   ", book);
        Assertions.assertFalse(test1);

        BooksEntity book2 = new BooksEntity();
        book2.setTitle("Test");

        boolean test2 = bookService.update("A0002", null);
        Assertions.assertFalse(test2);
    }

    @Test
    void testNullIdFail() {
        BooksEntity book = new BooksEntity();
        book.setTitle("Test");

        boolean update = bookService.update(null, book);
        Assertions.assertFalse(update);
    }
}
