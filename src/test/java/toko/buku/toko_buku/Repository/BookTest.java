package toko.buku.toko_buku.Repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Book.BooksRepository;
import toko.buku.toko_buku.Repository.Book.BooksRepositoryImpl;

public class BookTest {

    private BooksRepository booksRepository = new BooksRepositoryImpl();

    @Test
    void testInsert() {

        BooksEntity book = new BooksEntity();
        book.setAuthor("Tere Liye");
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good");
        book.setId("A0002");
        book.setIdCategori(new CategoriesEntity("Comedy"));
        book.setPages(143);
        book.setPublisher("Niponpaint Sdn. Bhd");
        book.setTitle("Hello");
        book.setYearOfPublish(LocalDateTime.of(2023, 4, 17, 0, 0));
        book.setUpdatedAt(LocalDateTime.now());

        boolean insert = booksRepository.insert(book);

        Assertions.assertTrue(insert);
    }

    @Test
    void testDelete() {

    }

    @Test
    void testUpdate() {
        BooksEntity booksEntity = new BooksEntity();
        // booksEntity.setAuthor("Tere Liye");
        booksEntity.setPages(213);
        // booksEntity.setTitle("Pulang");

        booksRepository.update("A0002", booksEntity);
    }
}
