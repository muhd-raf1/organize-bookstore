package toko.buku.toko_buku.Repository.Category;

import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface CategoriRepository {

    public boolean insert(CategoriesEntity category);

    public boolean delete(CategoriesEntity category);

    public void update(CategoriesEntity category);

    public void findBooksSameGenre();

}
