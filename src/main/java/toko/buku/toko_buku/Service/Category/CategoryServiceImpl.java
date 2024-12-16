package toko.buku.toko_buku.Service.Category;

import toko.buku.toko_buku.Entity.CategoriesEntity;
import toko.buku.toko_buku.Repository.Category.CategoriRepository;
import toko.buku.toko_buku.Repository.Category.CategoryRepositoryImpl;

public class CategoryServiceImpl implements CategoryService {

    private CategoriRepository categoriRepository = new CategoryRepositoryImpl();

    public CategoryServiceImpl(CategoriRepository categoriRepository) {
        this.categoriRepository = categoriRepository;
    }

    @Override
    public boolean add(CategoriesEntity category) {

        if (category != null) {
            categoriRepository.insert(category);
            return true;

        } else {
            return false;

        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void findBooksSameCategory() {

    }

}
