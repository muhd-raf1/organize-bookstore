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
        book.setAuthor("Rasya");
        // book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good");
        book.setId("A0006");
        book.setIdCategori(new CategoriesEntity("Horror"));
        book.setPages(143);
        book.setPublisher("Niponpaint Sdn. Bhd");
        book.setTitle("Test 2");
        book.setYearOfPublish(LocalDateTime.of(2034, 10, 07, 0, 0));
        // book.setUpdatedAt(LocalDateTime.now());

        boolean insert = booksRepository.insert(book);

        Assertions.assertTrue(insert);
    }

    @Test
    void testDelete() {

        BooksEntity booksEntity = new BooksEntity();
        booksEntity.setId("a");

        boolean delete = booksRepository.delete(booksEntity);

        Assertions.assertFalse(delete);
    }

    @Test
    void testUpdate() {
        BooksEntity book = new BooksEntity();
        book.setAuthor("RASYA");
        book.setCreatedAt(LocalDateTime.now());
        book.setDescription("This book is good v3");
        book.setId("A0003");
        book.setIdCategori(new CategoriesEntity("Horror"));
        book.setPages(143);
        book.setPublisher("Niponpaint Sdn. Bhd");
        book.setTitle("Who ?");
        book.setYearOfPublish(LocalDateTime.of(2034, 10, 07, 0, 0));
        book.setUpdatedAt(LocalDateTime.now());

        boolean update = booksRepository.update("A0003", book);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateCategoryId() {

        BooksEntity book = new BooksEntity();
        book.setTitle("test-test");
        book.setIdCategori(new CategoriesEntity("Romace"));

        boolean update = booksRepository.update("A0002", book);

        Assertions.assertFalse(update);
    }

    @Test
    void testUpdateCategoryIdNullButDataExist() {

        BooksEntity book = new BooksEntity();

        boolean update = booksRepository.update("A0002", book);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateCategoryIdNotExist() {

        BooksEntity book = new BooksEntity();
        book.setIdCategori(new CategoriesEntity("Hello"));

        boolean update = booksRepository.update("A0002", book);

        Assertions.assertFalse(update);
    }

    @Test
    void testUpdateIdCategorySuccess() {
        BooksEntity book = new BooksEntity();
        book.setIdCategori(new CategoriesEntity("Fantasy"));

        boolean update = booksRepository.update("A0002", book);

        Assertions.assertTrue(update);
    }

    @Test
    void testFindById() {
        BooksEntity test = booksRepository.findByIdOrTitle(new BooksEntity("A0003"));

        Assertions.assertNotNull(test);
    }

    @Test
    void testFindByNameBook() {
        BooksEntity booksEntity = new BooksEntity();
        // booksEntity.setId(null);
        booksEntity.setTitle("Pulang");

        BooksEntity test = booksRepository.findByIdOrTitle(booksEntity);

        Assertions.assertNotNull(test);
    }

    @Test
    void testFindByNameBookAndid() {
        BooksEntity booksEntity = new BooksEntity();
        booksEntity.setId("A0001");
        booksEntity.setTitle("Pulang");

        BooksEntity test = booksRepository.findByIdOrTitle(booksEntity);

        Assertions.assertNotNull(test);
    }

    // @Test
    // void testFindBooksSameGenreNull() {
    // BooksEntity book = new BooksEntity();
    // book.setIdCategori(new CategoriesEntity("Horror"));

    // List<BooksEntity> result = booksRepository.findSameCategory(book);
    // Assertions.assertNull(result);
    // }

    // @Test
    // void testFindBooksSameGenreSuccess() {
    // BooksEntity book = new BooksEntity();
    // book.setIdCategori(new CategoriesEntity("Romace"));

    // List<BooksEntity> result = booksRepository.findSameCategory(book);
    // Assertions.assertNotNull(result);
    // }
}
