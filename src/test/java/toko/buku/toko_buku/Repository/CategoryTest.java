package toko.buku.toko_buku.Repository;

import java.util.List;

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

    @Test
    void testFindBooksSameGenre() {
        List<CategoriesEntity> result = categoriRepository
                .findBooksSameGenre(new CategoriesEntity("Category"));
        Assertions.assertNotNull(result);
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
