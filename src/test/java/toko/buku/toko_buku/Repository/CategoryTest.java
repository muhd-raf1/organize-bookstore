package toko.buku.toko_buku.Repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;

public class CategoryTest {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();

    @Test
    void testInsert() {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setId("Ficton");
        categoriesEntity.setCategory("Ficton");

        boolean insert = categoriRepository.insert(categoriesEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testDelete() {

        boolean delete = categoriRepository.delete(new CategoriesEntity("Ficton"));

        Assertions.assertTrue(delete);
    }

    @Test
    void testFindBooksSameGenreNull() {
        BooksEntity bookTest = new BooksEntity();
        bookTest.setIdCategori(new CategoriesEntity("Horror"));

        List<BooksEntity> test = categoriRepository.findBooksSameGenre(bookTest);

        Assertions.assertNull(test);
    }

    @Test
    void testFindBooksSameGenreSuccess() {
        BooksEntity bookTest = new BooksEntity();
        bookTest.setIdCategori(new CategoriesEntity("Romace"));

        List<BooksEntity> test = categoriRepository.findBooksSameGenre(bookTest);

        Assertions.assertNotNull(test);

        for (BooksEntity booksEntity : test) {
            System.out.println(booksEntity.getTitle());
        }
    }

    @Test
    void testFindAllSuccess() {
        List<CategoriesEntity> all = categoriRepository.findAll();

        Assertions.assertNotNull(all);

        for (CategoriesEntity categoriesEntity : all) {
            System.out.println(categoriesEntity.getId());
        }
    }
}
