package toko.buku.toko_buku.Service.Category;

import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface CategoryService {

    public void add(CategoriesEntity category);

    public void delete(String id);

    public void findBooksSameCategory();

}
