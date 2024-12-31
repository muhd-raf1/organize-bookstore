package toko.buku.toko_buku.Service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;
import toko.buku.toko_buku.Service.Category.CategoryService;
import toko.buku.toko_buku.Service.Category.CategoryServiceImpl;

public class CategoryTest {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();
    private CategoryService categoryService = new CategoryServiceImpl(categoriRepository);

    @Test
    void testAddSuccess() {
        boolean b = categoryService.add(new CategoriesEntity("Romace", "Romance"));
        Assertions.assertTrue(b);
    }

    @Test
    void testAddNullNameAndIdFail() {
        boolean b = categoryService.add(new CategoriesEntity(null, null));
        Assertions.assertFalse(b);
    }

    @Test
    void testAddEmptyStringNameAndIdFail() {
        boolean b = categoryService.add(new CategoriesEntity("   ", "  "));
        Assertions.assertFalse(b);
    }

    @Test
    void testAddNullNameAndBlankStringIdFail() {
        boolean b = categoryService.add(new CategoriesEntity("", null));
        Assertions.assertFalse(b);
    }

    @Test
    void testAddNullNameFail() {
        boolean b = categoryService.add(new CategoriesEntity("Slice of life", null));
        Assertions.assertFalse(b);
    }

    @Test
    void testAddNullIdFail() {
        boolean b = categoryService.add(new CategoriesEntity(null, "null"));
        Assertions.assertFalse(b);
    }

    @Test
    void testFindBooksSameGenreSuccess() {
        List<BooksEntity> test = categoryService
                .findBooksSameCategory("Fantasy");
        Assertions.assertNotNull(test);

        for (BooksEntity booksEntity : test) {
            System.out.println(booksEntity.getTitle());
        }
    }

    @Test
    void testFindBookSameGenreNullFail() {
        List<BooksEntity> test = categoryService
                .findBooksSameCategory(null);

        Assertions.assertNull(test);
    }

    @Test
    void testFindBookSameGenreEmptyStringFail() {
        List<BooksEntity> test = categoryService
                .findBooksSameCategory("   ");

        Assertions.assertNull(test);
    }

    @Test
    void testFindBookSameGenreNotFoundFail() {
        List<BooksEntity> test = categoryService
                .findBooksSameCategory("tod");

        Assertions.assertNull(test);
    }

    @Test
    void testFindAllCategoriesSuccess() {
        List<CategoriesEntity> test = categoryService.findAllCategories();

        Assertions.assertNotNull(test);

        for (CategoriesEntity categoriesEntity : test) {
            System.out.println(categoriesEntity.getId());
        }
    }
}
