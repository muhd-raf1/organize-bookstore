package toko.buku.toko_buku.Service.Category;

import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface CategoryService {

    public boolean add(CategoriesEntity category);

    public void findBooksSameCategory();

}
