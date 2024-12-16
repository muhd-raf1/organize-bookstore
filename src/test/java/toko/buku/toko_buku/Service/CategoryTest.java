package toko.buku.toko_buku.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
