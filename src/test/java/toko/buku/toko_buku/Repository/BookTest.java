package toko.buku.toko_buku.Repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;

public class BookTest {

    private BooksRepository booksRepository;

    @Test
    void testInsert() {
        booksRepository = new BooksRepositoryImpl();

        BooksEntity book = new BooksEntity();
        book.setAuthor("Aidil Syahmi");
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good");
        book.setId("A0001");
        book.setIdCategori(new CategoriesEntity("Comedy"));
        book.setPages(124);
        book.setPublisher("Cahaya Sdn. Bhd");
        book.setTitle("Sang Kancil");
        book.setYearOfPublish(LocalDateTime.of(2010, 4, 17, 0, 0));
        book.setUpdatedAt(LocalDateTime.now());

        boolean insert = booksRepository.insert(book);

        Assertions.assertTrue(insert);
    }
}
