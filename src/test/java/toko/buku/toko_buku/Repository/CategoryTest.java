package toko.buku.toko_buku.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
