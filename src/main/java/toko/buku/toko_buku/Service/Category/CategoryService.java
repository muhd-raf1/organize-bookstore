package toko.buku.toko_buku.Service.Category;

import java.util.List;

import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface CategoryService {

    public boolean add(CategoriesEntity category);

    public List<CategoriesEntity> findAllCategories();

    public List<CategoriesEntity> findBooksSameCategory(String categoryName);

}
