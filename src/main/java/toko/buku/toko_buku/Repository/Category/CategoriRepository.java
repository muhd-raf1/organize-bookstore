package toko.buku.toko_buku.Repository.Category;

import java.util.List;

import toko.buku.toko_buku.Entity.BooksEntity;
import toko.buku.toko_buku.Entity.CategoriesEntity;

public interface CategoriRepository {

    public boolean insert(CategoriesEntity category);

    public boolean delete(CategoriesEntity category);

    public List<CategoriesEntity> findAll();

    public List<BooksEntity> findBooksSameGenre(BooksEntity booksEntity);

}
